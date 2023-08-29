/**
 * 
 */
package com.wibmo.rest;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.Notification;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.dto.AddCourseDto;
import com.wibmo.dto.DropCourseDTO;
import com.wibmo.dto.GradeCardResponseDTO;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.AdminOperation;
import com.wibmo.service.NotificationOperation;
import com.wibmo.service.PaymentOperation;
import com.wibmo.service.StudentOperation;
import com.wibmo.validator.ClientValidatorImpl;

/**
 * 
 */
@RestController
public class CRSStudentController {
	
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

	/**
	 * To add course preferences for the registration.
	 * @param userId
	 * @param addCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value="/student/{id}/addCourse",method = RequestMethod.POST)
	public ResponseEntity<String> addCourse(@PathVariable(value = "id") int userId,@RequestBody AddCourseDto addCourseDto)
	{
   		int isRegistered = studentOp.isStudentRegistered(userId);
		if(isRegistered>=1)
		{
			return new ResponseEntity<String>("\"Your Registration is completed. You can't add courses\";",HttpStatus.CONFLICT); 
			
		}
		
//		int primaryCount=0;
//		int alternativeCount=0;
//        for (Map.Entry<Integer,Integer> entry : addCourseDto.getCourses().entrySet()) 
//        {
//            if(entry.getValue()==0)
//            	primaryCount++;
//            else if(entry.getValue()==1)
//            	alternativeCount++;
//            else
//            	return new ResponseEntity<String>("Wrong Entry",HttpStatus.BAD_REQUEST);
//        }
//        
//        if(primaryCount<4)
//        {
//        	return new ResponseEntity<String>("Insufficient primary courses",HttpStatus.BAD_REQUEST);
//        }
//        else if(primaryCount>4)
//        {
//        	return new ResponseEntity<String>("Primary course exceed the limit",HttpStatus.BAD_REQUEST);
//        }
//        
//        if(alternativeCount<2)
//        {
//        	return new ResponseEntity<String>("Insufficient alternative courses",HttpStatus.BAD_REQUEST);
//        }
//        else if(alternativeCount>2)
//        {
//        	return new ResponseEntity<String>("Alternative course exceed the limit",HttpStatus.BAD_REQUEST);
//        }
       
 	   try {
		studentOp.addCourses(userId,addCourseDto);
		return new ResponseEntity<String>("Course Added Successfully",HttpStatus.OK); 
		
 	   } catch (CourseNotFoundException e) {
		return new ResponseEntity<String>("No Course added",HttpStatus.NOT_FOUND); 
		
 	   }
 	  catch (UserNotFoundException e) {
 			return new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND); 
 			
 	 	   }
 	   catch(CourseLimitExceededException e)
 	   {
		return new ResponseEntity<String>("Course Limit Exceeded",HttpStatus.CONFLICT); 
 	   }
 	   
		
	}
	
	
	
	/**
	 * To drop an already added course
	 * @param dropCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value="/student/{id}/dropCourse",method = RequestMethod.POST)
	public ResponseEntity<String> dropCourse(@RequestBody DropCourseDTO dropCourseDto)
	{
		int isRegistered = studentOp.isStudentRegistered(dropCourseDto.getStudentId());
    	if(isRegistered>=1)
		{
    		return new ResponseEntity<String>("\"Your Registration is completed. You can't drop courses\";",HttpStatus.CONFLICT); 
		}
       try {
 	   int coursePref = studentOp.dropCourses(dropCourseDto.getStudentId(),dropCourseDto.getCourseId()); 
 	   
 	   if(coursePref==0) {
   		return new ResponseEntity<String>("You have to add Complusory course",HttpStatus.CONFLICT); 

 	   }
 	   if(coursePref==1) {
 		  return new ResponseEntity<String>("You have to add Alternative course",HttpStatus.CONFLICT); 
 	   }
 	   }
 	   catch(UserNotFoundException e) {
 		   
 		  return new ResponseEntity<String>("User with id "+e.getUserId()+" is not found",HttpStatus.NOT_FOUND); 
 		   
 	   } catch(CourseNotFoundException e) {
 		  return new ResponseEntity<String>("Course with id "+e.getCourseId()+" is not found",HttpStatus.NOT_FOUND); 
 	   }
       return new ResponseEntity<String>("Drop is Successful!! ",HttpStatus.OK); 
	}
	
	
	/**
	 * To apply for the course registration after the addition of courses has been completed.
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/student/{id}/registerCourse",method = RequestMethod.POST)	
	public ResponseEntity<?> registerCourses(@PathVariable(value="id") int userId)
	{try {
		studentOp.registerCourses(userId);
		return ResponseEntity.ok("Applied For Course Registration Successfully");
		}
		 catch(UserNotFoundException e) {
	 		   
	 		  return new ResponseEntity<String>("User with id "+e.getUserId()+" is not found",HttpStatus.NOT_FOUND); 
	 		   
	 	   } 
	}
	
	/**
	 * To return list of registered courses
	 * @param userId
	 * @return a map of registered courses and their course names.
	 */
	@RequestMapping(value="/student/{id}/listCourse",method = RequestMethod.POST)
	public ResponseEntity<Map<Integer,String>> listCourse(@PathVariable(value="id") int userId)
	{
 	   try {
 		  Map<Integer,String> courses=studentOp.listCourse(userId);
 			if(courses.size()==0)
 			{
 				
 		       return new ResponseEntity("Course Registration pending",HttpStatus.TOO_EARLY); 
 				
 			}
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
	public ResponseEntity<GradeCardResponseDTO> viewReportCard(@PathVariable(value="id") int userId)
	{
 	   try {
 	   return ResponseEntity.ok(studentOp.viewReportCard(userId)); 
 	   }      
 	   catch(UserNotApprovedException e) {
 		   return new ResponseEntity("Courses of student with id "+e.getUserId()+" is not approved by admin",HttpStatus.NOT_FOUND);
 	   }
	}
	
	@RequestMapping(value="/student/viewCourseCatalog",method = RequestMethod.GET)
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
	public ResponseEntity viewNotifications(@PathVariable(name = "id") int studentId)
	{try {
		List<Notification>notifications=notificationOp.getNotificationMessage(studentId);
		if(notifications!=null)
		{
			return ResponseEntity.ok(notifications);
			
		}
		else
		{
	 		 return new ResponseEntity("No Notifications",HttpStatus.NOT_FOUND);
		}}
		
		 catch(UserNotFoundException e) {
	 		   
	 		  return new ResponseEntity<String>("User with id "+e.getUserId()+" is not found",HttpStatus.NOT_FOUND); 
	 		   
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
	public ResponseEntity<String> payFee(@PathVariable(value="id") int userId,@PathVariable(value="paymentMethod") String paymentMethod ,@RequestParam(required = false) String onlineMethod) {
		  boolean status=false;
		 
		 if(studentOp.isApproved(userId)>0) {
			 
	    	   if(paymentMethod.equals("offline"))
	    	   {
	    		   status=payment.offline(userId);
	    		  
	 	  		  payment.recordPayment(userId, status);
	 	  		 
	 	  		  
	    	   }
	    	   if(paymentMethod.equals("online")) {
	    		  if(onlineMethod.equals("UPI")) {
	    			  status=payment.UPI(userId);
	        		  payment.recordPayment(userId, status);
	    		  }
	    		  else if(onlineMethod.equals("cards")) {
	    			  status=payment.cards(userId);
	        		  payment.recordPayment(userId, status);
	    		  }
	    		  else if(onlineMethod.equals("Wallet")) {
	    			  status=payment.wallet(userId);
	        		  payment.recordPayment(userId, status);  
	    		  }
	    		   
	    		  payment.recordPayment(userId, status); 
	    	   }
	    	   if(status==true)
	 	  	  {
	 	  		  notificationOp.sendNotification(NotificationConstants.PAYMENT_SUCCESS_NOTIFICATION, userId);
	 	  		  return new ResponseEntity<String>("Payment Successful",HttpStatus.OK);

	 	  	  }
	 	  	  	
	 	  	  else
	 	  	  {
	 				  notificationOp.sendNotification(NotificationConstants.PAYMENT_REJECTED_NOTIFICATION, userId);
	 				 return new ResponseEntity<String>("Payment Failed",HttpStatus.NOT_ACCEPTABLE);
	 	  	  }
	    	   }
	     else {
	    	 return new ResponseEntity<String>("Student courses are not approved by admin",HttpStatus.NOT_ACCEPTABLE);
	    	   }
		 
	}
	
	
	
	

	

}
