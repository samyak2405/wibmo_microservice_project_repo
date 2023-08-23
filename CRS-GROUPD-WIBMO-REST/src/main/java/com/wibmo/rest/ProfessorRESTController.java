package com.wibmo.rest;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.model.User;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.model.CourseCatalog;
import com.wibmo.service.AdminOperationImpl;

import com.wibmo.service.ProfessorOperation;
import com.wibmo.service.ProfessorOperationImpl;
import com.wibmo.validator.ClientValidatorImpl;

@RestController
public class ProfessorRESTController {
     @Autowired
	private ProfessorOperation professorOp;
     @Autowired
	public ClientValidatorImpl clientValidator ;
     @Autowired
     Scanner scan;
    final static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
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
	List<Integer> courseIdList =new ArrayList<>();
	@RequestMapping(value="/professor/requestcourse",method = RequestMethod.POST)
	public void requestCourse(@RequestBody int courseId) {
		
		professorOp.viewCourseCatalog();
//		log.info("Enter courseid: ");
//        int courseid1=scan.nextInt();
        courseIdList.add(courseId);
	}
	
	@RequestMapping(value="/professor/freezelist",method = RequestMethod.POST)
	public void freezeList() {
		 try {
         	
				professorOp.requestCourseOffering(userId,courseIdList);
			} catch (CourseNotFoundException e) {
				log.info("Course with course id:"+e.getCourseId()+" Not Found");
			}
	}
	
	
	
	@RequestMapping(value="/professor/{userId}/studentlist",method = RequestMethod.GET)
    public void studentList(@PathVariable(value="userId") int userId,@RequestBody int courseId) {
		
		
		
    	log.info("List of Courses assigned");
 	   professorOp.listOfApprovedCourses(userId);

        try {
     	   professorOp.viewStudentList(courseId);
		} catch (CourseNotFoundException e) {
			log.info("Course with course id:"+e.getCourseId()+" Not Found");

		}
	}
	
	
	
	
	@RequestMapping(value="/professor/coursecatalog",method = RequestMethod.GET)
	public void courseCatalog() {
		
		professorOp.viewCourseCatalog();
		
	}
	
	
	
	@RequestMapping(value="/professor/setgrades",method = RequestMethod.POST)
	public void setGrades(@RequestBody int courseId,int studentId,String grade ) {
		   log.info("Choose Course from below given list");
	   	   professorOp.listOfApprovedCourses(userId);
//	   	   log.info("Course Id");
//    	   int courseId=scan.nextInt();
    	   log.info("Following are the students registered for the course "+courseId);
    	   try {
        	   professorOp.viewStudentList(courseId);
		} catch (CourseNotFoundException e) {
			log.info("Course with course id:"+e.getCourseId()+" Not Found");

		}
//	   	   log.info("Enter the StudentId");
//    	   int studentId=scan.nextInt();
  
//    	   log.info("Enter grades");
//    	   String grade=scan.next();
	try {
		professorOp.setGrades(studentId,courseId,grade);
	} catch (UserNotFoundException e) {
		log.info("Student with id:"+e.getUserId()+" Not Found");
		
	}catch(CourseNotFoundException e)
	{
		log.info("Course with id:"+e.getCourseId()+" Not Found");
	}
		
	}
	
	
	
	
	
	
	@RequestMapping(value="/professor/registration",method = RequestMethod.POST)
	public void professorRegistration(@RequestBody String name,String email,long phonenumber) {
		// TODO Auto-generated method stub
				log.info("Enter the Details for Registration");
				User user = new User();
				log.info("\nEnter Name: ");
				user.setUserName(name);
				log.info("\nEnter Email: ");
				user.setUserEmail(email);
				String password = clientValidator.passwordValidator();
				user.setUserPassword(password);
				
				log.info("\nEnter Phone Number: ");
				log.info("");
				user.setUserPhonenumber(phonenumber);
		      	 log.info("===================================================================================");
				
				try {
					professorOp.registerProfessor(user);
				} catch (UserAlreadyExistsException e) {
					log.info("User with user id:"+e.getUserId()+" Already Exists");

				}
				
			
	}
	
}
