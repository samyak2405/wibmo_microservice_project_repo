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
	
	
	
	
//	public int addCompulsoryCourse(AddCourseDto addCourseDto,int compulsory) {
//		int compulsoryCourses = compulsory;
//		int course = 0;
//		while(compulsoryCourses > 0) {
//	 		   System.out.print("\nEnter the Course Id: ");
//	     	   int courseId = scan.nextInt();
//	     	   course = courseId;
//	     	   if(addCourseDto.getCourses().containsKey(courseId))
//	     	   {
//	     		   log.info("You entered duplicate choice. Please add another Course");
//	     		   continue;
//	     	   }
//	     	   addCourseDto.getCourses().put(courseId, 0);
//	     	  compulsoryCourses--;
//		}
//		return course;
//	}
	
//	public int addAlternativeCourse(AddCourseDto addCourseDto,int alternative) {
//		int alternativeCourses = alternative;
//		int course = 0;
//		while(alternativeCourses > 0) {
//	 		   System.out.print("\nEnter the Course Id: ");
//	     	   int courseId = scan.nextInt();
//	     	   course = courseId;
//	     	   if(addCourseDto.getCourses().containsKey(courseId))
//	     	   {
//	     		   log.info("You entered duplicate choice. Please add another Course");
//	     		   continue;
//	     	   }
//	     	  addCourseDto.getCourses().put(courseId, 1);
//	     	  alternativeCourses--;
//		}
//		return course;
//	}
	
	
	
	
	@RequestMapping(value="/student/{id}/addCourse",method = RequestMethod.POST)
	public String addCourse(@PathVariable(value = "id") int userId,@RequestBody AddCourseDto addCourseDto)
	{
		
   		int isRegistered = studentOp.isStudentRegistered(userId);
		//log.info("Student Registration Status: "+isRegistered);
		if(isRegistered==1)
		{
			return "Your Registration is completed. You can't add courses";
			
		}
// 	   return "\nSelect Course Ids from below given Course Catalog";
//  	   System.out.print("\nYou have to select 4 complusory and 2 Alternative");
//  	   log.info("\nFor complusory enter 0 and for alternative enter 1");
//  	   
//		log.info("");
//
//        log.info("===================================================================================");
// 	   studentOp.viewCourseCatalog();
// 	  log.info("");
//
//      log.info("===================================================================================");
		StudentCourseMap studCoMap = new StudentCourseMap();
// 	   studCoMap.setStudentId(userId);
// 	   log.info("Add Compulsory Courses");
// 	   this.addCompulsoryCourse(addCourseDto,4);
// 	   log.info("Add Alternative Courses");
// 	   this.addAlternativeCourse(addCourseDto,2);
 	   studCoMap.setCourses(addCourseDto.getCourses());
 	   try {
		studentOp.addCourses(studCoMap);
		return "Course Added Successfully.";
	} catch (CourseNotFoundException e) {
		return "Course with course id: "+e.getCourseId()+" Not Found!!";
	}catch(CourseLimitExceededException e)
 	   {
		return "Course Limit Exceeded";
 	   }
 	   
		
	}
	
	
	
	@RequestMapping(value="/student/{id}/dropCourse",method = RequestMethod.POST)
	public String dropCourse(@RequestBody DropCourseDTO dropCourseDto)
	{
		int isRegistered = studentOp.isStudentRegistered(dropCourseDto.getStudentId());
    	if(isRegistered==1)
		{
			return "Your Registration is completed. You can't drop courses";
		}
       try {
 	   int coursePref = studentOp.dropCourses(dropCourseDto.getStudentId(),dropCourseDto.getCourseId()); 
 	   //courses.remove(dropCourseDto.getCourseId());
 	   
 	   if(coursePref==0) {
 		   return "You have to add Complusory course";
 	   }
 	   if(coursePref==1) {
 		   return "You have to add Alternative course";
 	   }
 	   }
 	   catch(UserNotFoundException e) {
 		   return "User with id "+e.getUserId()+" is not found";
 	   } catch(CourseNotFoundException e) {
 		   return "Course with id "+e.getCourseId()+" is not found";
 	   }
       return "Drop Successfull";
	}
	
	@RequestMapping(value="/student/{id}/registerCourse",method = RequestMethod.POST)	
	public void registerCourses(@PathVariable int userId)
	{
		studentOp.registerCourses(userId);
	}
	
	
	
	@RequestMapping(value="/student/{id}/listCourse",method = RequestMethod.POST)
	public void listCourse(@PathVariable int userId)
	{
 	   try {
 	   studentOp.listCourse(userId);}
 	   catch(UserNotApprovedException e) {
 		   log.info("User with id "+e.getUserId()+" is not approved by admin");
 	   }
	}
	
	
	
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
	
	
	
	@RequestMapping(value="/student/{id}/viewCourseCatalog",method = RequestMethod.GET)
	public ResponseEntity<List<CourseCatalog>> viewCourseCatalog()
	{
		return ResponseEntity.ok(studentOp.viewCourseCatalog()); 
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
	    		  if(onlineMethod=="UPI") {
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
