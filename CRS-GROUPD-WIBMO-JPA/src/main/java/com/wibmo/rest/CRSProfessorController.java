package com.wibmo.rest;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.LogManager;


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

import com.wibmo.entity.User;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.Student;
import com.wibmo.service.*;

import com.wibmo.service.ProfessorOperation;
import com.wibmo.service.ProfessorOperationImpl;
import com.wibmo.validator.ClientValidatorImpl;

@RestController
public class CRSProfessorController {
     @Autowired
	private ProfessorOperation professorOp;
     @Autowired
	public ClientValidatorImpl clientValidator ;
    
    Scanner scan=new Scanner(System.in);
  
    public Logger log =LogManager.getLogger();
    
    
    String userEmail;
	int userId;

	
	
	
	public CRSProfessorController()
	{
		
	}
	
	
	public CRSProfessorController(String userEmail)
	{
		this.userEmail=userEmail;
		userId = professorOp.getProfessorById(userEmail);
	}
	
	
	
	/**
	 * To request the courses.  
	 * @param userId
	 * @param courseIdList
	 * @return message if the courses requested by professor  are successfully sent to admin for Approval or not
	 */
	
	@RequestMapping(value="/professor/{userId}/requestcourse",method = RequestMethod.POST)
	public ResponseEntity freezeList(@PathVariable(value="userId") int userId,@RequestBody List<Integer> courseIdList) {
		 try {
         	
				professorOp.requestCourseOffering(userId,courseIdList);
				return new ResponseEntity("Courses request sent for approval to Admin",HttpStatus.OK);
			} catch (CourseNotFoundException e) {
				return new ResponseEntity("Course with course id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);
			}
	}
	
	
	
	
	
	
	
	/**
	 * To view the list of approved courses
	 * @param userId
	 * @return list of approved courses
	 */
	@RequestMapping(value="/professor/{userId}/approvedcourses",method = RequestMethod.GET)
	public ResponseEntity<Map<Integer,String>> approvedCourses(@PathVariable(value="userId") int userId)
	{
		 return new ResponseEntity(professorOp.listOfApprovedCourses(userId),HttpStatus.OK);
	}	
	
	
	
	
	
	/**
	 * To view list of students registered for particular course.
	 * @param userId
	 * @param courseId
	 * @return list of students registered for particular courseId.
	 */
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
	
	
	
	
	
	/**
	 * To view course catalog
	 * @return list of courses in course catalog
	 */
	@RequestMapping(value="/professor/coursecatalog",method = RequestMethod.GET)
	public ResponseEntity<List<CourseCatalog>> courseCatalog() {
		
		return ResponseEntity.ok(professorOp.viewCourseCatalog());
		
	}
	
	
	
	
	
	/**
	 * To set the grades of students.
	 * @param gradecard
	 * @return message if professor has set the grades successfully or not .
	 */
	
	@RequestMapping(value="/professor/setgrades",method = RequestMethod.POST)
	public ResponseEntity  setGrades(@RequestBody List<GradeCard> gradecard ) {
		   

	try {
		for(GradeCard gradeCard: gradecard) {
			professorOp.setGrades(gradeCard.getStudentId(),gradeCard.getCourseId(),gradeCard.getGrade());
		}
		return new ResponseEntity("Added grades",HttpStatus.OK);
	} catch (UserNotFoundException e) {
		return new ResponseEntity("Student with id:"+e.getUserId()+" Not Found",HttpStatus.NOT_FOUND);
		
	}catch(CourseNotFoundException e)
	{
		return new ResponseEntity("Course with id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);
	}
		
	}
	
	
	
	
	
	
	/**
	 * Professor Registration
	 * @param user
	 * @return message if professor is successfully registered or not
	 */
	
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
////			
////	}
//	
//	}
}
