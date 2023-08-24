package com.wibmo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Loggers;
//import org.apache.log4j.LogManager;
import org.apache.logging.log4j.core.config.Loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.model.User;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.model.CourseCatalog;
import com.wibmo.model.GradeCard;
import com.wibmo.model.Student;
import com.wibmo.service.*;

import com.wibmo.service.ProfessorOperation;
import com.wibmo.service.ProfessorOperationImpl;
import com.wibmo.validator.ClientValidatorImpl;
import org.apache.logging.log4j.core.config.Loggers;

@RestController
public class ProfessorRESTController {
     @Autowired
	private ProfessorOperation professorOp;
     @Autowired
	public ClientValidatorImpl clientValidator ;
    
     Scanner scan=new Scanner(System.in);
  
    public Logger log =LogManager.getLogger();
    
    
    String userEmail;
	int userId;

	
	
	
	public ProfessorRESTController()
	{
		
	}
	
	
	public ProfessorRESTController(String userEmail)
	{
		this.userEmail=userEmail;
		userId = professorOp.getProfessorById(userEmail);
	}
	
	
	
//	List<Integer> courseIdList =new ArrayList<>();
//	@RequestMapping(value="/professor/{userId}/requestcourse",method = RequestMethod.POST)
//	public  void requestCourse(@PathVariable(value="userId") int userId,@RequestBody int courseId) {
//		
////		professorOp.viewCourseCatalog();
//
//        courseIdList.add(courseId);
//	}
//	
	
	
	@RequestMapping(value="/professor/{userId}/requestcourse",method = RequestMethod.POST)
	public ResponseEntity freezeList(@PathVariable(value="userId") int userId,@RequestBody List<Integer> courseIdList) {
		 try {
         	
				professorOp.requestCourseOffering(userId,courseIdList);
				return new ResponseEntity(HttpStatus.OK);
			} catch (CourseNotFoundException e) {
				return new ResponseEntity("Course with course id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);
			}
	}
	
	
	
	
	@RequestMapping(value="/professor/{userId}/approvedcourses",method = RequestMethod.GET)
	public ResponseEntity<Map<Integer,String>> approvedCourses(@PathVariable(value="userId") int userId)
	{
		 return new ResponseEntity(professorOp.listOfApprovedCourses(userId),HttpStatus.OK);
	}	
	
	
	
	
	@RequestMapping(value="/professor/{userId}/{courseId}/studentlist",method = RequestMethod.POST)
    public ResponseEntity<List<Student>> studentList(@PathVariable(value="userId") int userId,@PathVariable(value="courseId") int courseId) {
		
//    	log.info("List of Courses assigned");
// 	      professorOp.listOfApprovedCourses(userId);

        try {
     	    return new ResponseEntity(professorOp.viewStudentList(courseId),HttpStatus.OK);
		} catch (CourseNotFoundException e) {
			return new ResponseEntity("Course with course id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);

		}
	}
	
	
	
	
	@RequestMapping(value="/professor/coursecatalog",method = RequestMethod.GET)
	public ResponseEntity<List<CourseCatalog>> courseCatalog() {
		
		return ResponseEntity.ok(professorOp.viewCourseCatalog());
		
	}
	
	
	
	@RequestMapping(value="/professor/setgrades",method = RequestMethod.POST)
	public ResponseEntity  setGrades(@RequestBody GradeCard gradecard ) {
		   log.info("Choose Course from below given list");
	   	   professorOp.listOfApprovedCourses(userId);
//	   	   log.info("Course Id");
//    	   int courseId=scan.nextInt();
//    	   log.info("Following are the students registered for the course "+courseId);
//    	   try {
//        	   professorOp.viewStudentList(gradecard.getCourseId());
//		} catch (CourseNotFoundException e) {
//			log.info("Course with course id:"+e.getCourseId()+" Not Found");
//
//		}
//	   	   log.info("Enter the StudentId");
//    	   int studentId=scan.nextInt();
  
//    	   log.info("Enter grades");
//    	   String grade=scan.next();
	try {
		professorOp.setGrades(gradecard.getStudentId(),gradecard.getCourseId(),gradecard.getGrade());
		return new ResponseEntity(HttpStatus.OK);
	} catch (UserNotFoundException e) {
		return new ResponseEntity("Student with id:"+e.getUserId()+" Not Found",HttpStatus.NOT_FOUND);
		
	}catch(CourseNotFoundException e)
	{
		return new ResponseEntity("Course with id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);
	}
		
	}
	
	
	
	
	
//	
//	@RequestMapping(value="/professor/registration",method = RequestMethod.POST)
//	public ResponseEntity professorRegistration(@RequestBody User user) {
//		// TODO Auto-generated method stub
//////				log.info("Enter the Details for Registration");
////				User user = new User();
//////				log.info("\nEnter Name: ");
////				user.setUserName(name);
//////				log.info("\nEnter Email: ");
////				user.setUserEmail(email);
//			//	String password = clientValidator.passwordValidator();
////				user.setUserPassword(password);
////				
//////				log.info("\nEnter Phone Number: ");
//////				log.info("");
////				user.setUserPhonenumber(phonenumber);
////		      	 log.info("===================================================================================");
////				
//				try {
//					professorOp.registerProfessor(user);
//					return new ResponseEntity(HttpStatus.OK);
//				} catch (UserAlreadyExistsException e) {
//					return new ResponseEntity("User with user id:"+e.getUserId()+" Already Exists",HttpStatus.FOUND);
//
//				}
//				
//			
//	}
	
}
