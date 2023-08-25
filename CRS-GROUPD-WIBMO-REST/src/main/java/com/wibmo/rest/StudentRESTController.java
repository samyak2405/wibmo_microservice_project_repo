/**
 * 
 */
package com.wibmo.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Loggers;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.model.CourseCatalog;
import com.wibmo.model.GradeCard;
import com.wibmo.model.Notification;
import com.wibmo.model.StudentCourseMap;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.dto.AddCourseDto;
import com.wibmo.dto.DropCourseDTO;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.AdminOperation;
import com.wibmo.service.AdminOperationImpl;
import com.wibmo.service.NotificationOperation;
import com.wibmo.service.NotificationOperationImpl;
import com.wibmo.service.PaymentOperation;
import com.wibmo.service.StudentOperation;
import com.wibmo.service.StudentOperationImpl;
import com.wibmo.validator.ClientValidatorImpl;

/**
 * 
 */
@RestController
public class StudentRESTController {
	
	@Autowired
	public StudentOperation studentOp;
	
	@Autowired
	public AdminOperation adminOp;
	
	@Autowired
	public NotificationOperation notificationOp;
	
	@Autowired
	public ClientValidatorImpl clientValidator;
	
	@Autowired
	public PaymentOperation payment;
	Scanner scan=new Scanner(System.in);
	
	
	
	public Logger log=LogManager.getLogger();
	
	@GetMapping("/student")
	public String check()
	{
		return "hello";
	}
	
	
	/**
	 * To add course preferences for the registration.
	 * @param userId
	 * @param addCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value="/student/{id}/addCourse",method = RequestMethod.POST)
	public ResponseEntity addCourse(@PathVariable(value = "id") int userId,@RequestBody AddCourseDto addCourseDto)
	{
   		int isRegistered = studentOp.isStudentRegistered(userId);
		//log.info("Student Registration Status: "+isRegistered);
		if(isRegistered==1)
		{
			return new ResponseEntity("\"Your Registration is completed. You can't add courses\";",HttpStatus.CONFLICT); 
			
		}
		
		int primaryCount=0;
		int alternativeCount=0;
        for (Map.Entry<Integer,Integer> entry : addCourseDto.getCourses().entrySet()) 
        {
            if(entry.getValue()==0)
            	primaryCount++;
            else if(entry.getValue()==1)
            	alternativeCount++;
            else
            	return new ResponseEntity("Wrong Entry",HttpStatus.BAD_REQUEST);
        }
        
        if(primaryCount<4)
        {
        	return new ResponseEntity("Insufficient primary courses",HttpStatus.BAD_REQUEST);
        }
        else if(primaryCount>4)
        {
        	return new ResponseEntity("Primary course exceed the limit",HttpStatus.BAD_REQUEST);
        }
        
        if(alternativeCount<2)
        {
        	return new ResponseEntity("Insufficient alternative courses",HttpStatus.BAD_REQUEST);
        }
        else if(alternativeCount>2)
        {
        	return new ResponseEntity("Alternative course exceed the limit",HttpStatus.BAD_REQUEST);
        }
        
		
		StudentCourseMap studCoMap = new StudentCourseMap();
 	   studCoMap.setCourses(addCourseDto.getCourses());
 	   try {
		studentOp.addCourses(studCoMap);
		return new ResponseEntity("\"Your Registration is completed. You can't add courses\";",HttpStatus.OK); 
		
 	   } catch (CourseNotFoundException e) {
		return new ResponseEntity("\"Your Registration is completed. You can't add courses\";",HttpStatus.NOT_FOUND); 
 	   }catch(CourseLimitExceededException e)
 	   {
		return new ResponseEntity("Course Limit Exceeded",HttpStatus.CONFLICT); 
 	   }
 	   
		
	}
	
	
	
	/**
	 * To drop an already added course
	 * @param dropCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value="/student/{id}/dropCourse",method = RequestMethod.POST)
	public ResponseEntity dropCourse(@RequestBody DropCourseDTO dropCourseDto)
	{
		int isRegistered = studentOp.isStudentRegistered(dropCourseDto.getStudentId());
    	if(isRegistered==1)
		{
    		return new ResponseEntity("\"Your Registration is completed. You can't drop courses\";",HttpStatus.CONFLICT); 
		}
       try {
 	   int coursePref = studentOp.dropCourses(dropCourseDto.getStudentId(),dropCourseDto.getCourseId()); 
 	   
 	   if(coursePref==0) {
   		return new ResponseEntity("You have to add Complusory course",HttpStatus.CONFLICT); 

 	   }
 	   if(coursePref==1) {
 		  return new ResponseEntity("You have to add Alternative course",HttpStatus.CONFLICT); 
 	   }
 	   }
 	   catch(UserNotFoundException e) {
 		   
 		  return new ResponseEntity("User with id "+e.getUserId()+" is not found",HttpStatus.NOT_FOUND); 
 		   
 	   } catch(CourseNotFoundException e) {
 		  return new ResponseEntity("Course with id "+e.getCourseId()+" is not found",HttpStatus.NOT_FOUND); 
 	   }
       return new ResponseEntity("Drop is Successful!! ",HttpStatus.OK); 
	}
	
	
	/**
	 * To apply for the course registration after the addition of courses has been completed.
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/student/{id}/registerCourse",method = RequestMethod.POST)	
	public ResponseEntity<String> registerCourses(@PathVariable int userId)
	{
		studentOp.registerCourses(userId);
		return ResponseEntity.ok("Applied For Course Registration Successfully");
	}
	
	/**
	 * To return list of registered courses
	 * @param userId
	 * @return a map of registered courses and their course names.
	 */
	@RequestMapping(value="/student/{id}/listCourse",method = RequestMethod.POST)
	public ResponseEntity<Map<Integer,String>> listCourse(@PathVariable int userId)
	{
 	   try {
 		  Map<Integer,String> courses=studentOp.listCourse(userId);
 			if(courses.size()==0)
 			{
 				
 		       return new ResponseEntity("Course Registration pending",HttpStatus.TOO_EARLY); 
 				
 			}
 			else 
 				return ResponseEntity.ok(courses);
 	   }
 	   catch(UserNotApprovedException e) {
 		  return new ResponseEntity("User with id "+e.getUserId()+" is not approved by admin",HttpStatus.NOT_FOUND); 
 	   }
	}
	
	
	
	/**
	 * To view the grade card
	 * @param userId
	 * @return a list of grades.
	 */
	@RequestMapping(value="/student/{id}/viewReportCard",method = RequestMethod.GET)
	public ResponseEntity<List<GradeCard>> viewReportCard(@PathVariable(value="id") int userId)
	{
 	   try {
 	   return ResponseEntity.ok(studentOp.viewReportCard(userId)); 
 	   }      
 	   catch(UserNotApprovedException e) {
 		   return new ResponseEntity("Courses of student with id "+e.getUserId()+" is not approved by admin",HttpStatus.NOT_FOUND);
 	   }
	}
	
	/**
	 * To view list of courses in the course catalog.
	 * @return the list of available courses.
	 */
	@RequestMapping(value="/student/{id}/viewCourseCatalog",method = RequestMethod.GET)
	public ResponseEntity<List<CourseCatalog>> viewCourseCatalog()
	{
		return ResponseEntity.ok(studentOp.viewCourseCatalog()); 
	}
	
	/**
	 * To view the notifications
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value="/student/{id}/viewNotifications",method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> viewNotifications(@PathVariable(name = "id") int studentId)
	{
		List<Notification>notifications=notificationOp.getNotificationMessage(studentId);
		if(notifications!=null)
		{
			return ResponseEntity.ok(notifications);
			
		}
		else
		{
	 		 return new ResponseEntity("No Notifications",HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	/**
	 * payFee
	 * @param userId
	 * @param paymentMethod
	 * @param onlineMethod
	 * @return message if payment is successful or not
	 */
	
	@RequestMapping(value="/student/{id}/payfee/{paymentMethod}",method = RequestMethod.POST)
	public ResponseEntity payFee(@PathVariable(value="id") int userId,@PathVariable(value="paymentMethod") String paymentMethod ,@RequestParam(required = false) String onlineMethod) {
		  boolean status=false;
		 if(studentOp.isApproved(userId)) {
			 
	    	   if(paymentMethod.equals("offline"))
	    	   {
	    		   status=payment.offline(userId);
	    		   System.out.println(status);
	 	  		  payment.recordPayment(userId, status);
	 	  		  
	    	   }
	    	   if(paymentMethod.equals("online")) {
	    		  if(onlineMethod.equals("UPI")) {
	    			  status=payment.UPI(userId);
	        		  payment.recordPayment(userId, status);
	    		  }
	    		  else if(onlineMethod.equals("Cards")) {
	    			  status=payment.cards(userId);
	        		  payment.recordPayment(userId, status);
	    		  }
	    		  else if(onlineMethod.equals("Wallet")) {
	    			  status=payment.wallet(userId);
	        		  payment.recordPayment(userId, status);  
	    		  }
	    		   
	    		   
	    	   }
	    	   if(status==true)
	 	  	  {
	 	  		  notificationOp.sendNotification(NotificationConstants.PAYMENT_SUCCESS_NOTIFICATION, userId);
	 	  		  return new ResponseEntity("Payment Successful",HttpStatus.OK);

	 	  	  }
	 	  	  	
	 	  	  else
	 	  	  {
	 				  notificationOp.sendNotification(NotificationConstants.PAYMENT_REJECTED_NOTIFICATION, userId);
	 				 return new ResponseEntity("Payment Failed",HttpStatus.NOT_ACCEPTABLE);
	 	  	  }
	    	   }
	     else {
	    	 return new ResponseEntity("Student courses are not approved by admin",HttpStatus.NOT_ACCEPTABLE);
	    	   }
		 
	}
	
	
	
	

	

}
