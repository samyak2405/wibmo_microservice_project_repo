/**
 * 
 */
package com.wibmo.rest;

import java.util.Map;
import java.util.Scanner;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.model.StudentCourseMap;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.AdminOperationImpl;
import com.wibmo.service.NotificationOperation;
import com.wibmo.service.NotificationOperationImpl;
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
	public NotificationOperation notificationOp;
	
	@Autowired
	public ClientValidatorImpl clientValidator;
	Scanner scan=new Scanner(System.in);
	
	Map<Integer,Integer> courses;
	
	final static Logger log = Logger.getLogger(StudentRESTController.class.getName());

	
	
	public int addCompulsoryCourse(int compulsory) {
		int compulsoryCourses = compulsory;
		int course = 0;
		while(compulsoryCourses > 0) {
	 		   System.out.print("\nEnter the Course Id: ");
	     	   int courseId = scan.nextInt();
	     	   course = courseId;
	     	   if(courses.containsKey(courseId))
	     	   {
	     		   log.info("You entered duplicate choice. Please add another Course");
	     		   continue;
	     	   }
	     	   courses.put(courseId, 0);
	     	  compulsoryCourses--;
		}
		return course;
	}
	
	public int addAlternativeCourse(int alternative) {
		int alternativeCourses = alternative;
		int course = 0;
		while(alternativeCourses > 0) {
	 		   System.out.print("\nEnter the Course Id: ");
	     	   int courseId = scan.nextInt();
	     	   course = courseId;
	     	   if(courses.containsKey(courseId))
	     	   {
	     		   log.info("You entered duplicate choice. Please add another Course");
	     		   continue;
	     	   }
	     	   courses.put(courseId, 1);
	     	  alternativeCourses--;
		}
		return course;
	}
	
	
	
	
	@RequestMapping(value="/student/{id}/addCourse",method = RequestMethod.POST)
	public void addCourse(@PathVariable int userId)
	{
   		int isRegistered = studentOp.isStudentRegistered(userId);
		log.info("Student Registration Status: "+isRegistered);
		if(isRegistered==1)
		{
			log.info("Your Registration is completed. You can't add courses");
			return;
		}
 	   log.info("\nSelect Course Ids from below given Course Catalog");
  	   System.out.print("\nYou have to select 4 complusory and 2 Alternative");
  	   log.info("\nFor complusory enter 0 and for alternative enter 1");
  	   
		log.info("");

        log.info("===================================================================================");
 	   studentOp.viewCourseCatalog();
 	  log.info("");

      log.info("===================================================================================");
 	   StudentCourseMap studCoMap = new StudentCourseMap();
 	   studCoMap.setStudentId(userId);
 	   log.info("Add Compulsory Courses");
 	   addCompulsoryCourse(4);
 	   log.info("Add Alternative Courses");
 	   addAlternativeCourse(2);
 	   
 	   studCoMap.setCourses(courses);
 	   try {
		studentOp.addCourses(studCoMap);
		log.info("Course Added Successfully.");
	} catch (CourseNotFoundException e) {
		log.info("Course with course id: "+e.getCourseId()+" Not Found!!");
	}catch(CourseLimitExceededException e)
 	   {
		log.info("Course Limit Exceeded");
 	   }
 	   log.info("Course Added Successfully.");
		
	}
	
	
	@RequestMapping(value="/student/{id}/dropCourse",method = RequestMethod.POST)
	public void dropCourse(@PathVariable int userId)
	{
		int isRegistered = studentOp.isStudentRegistered(userId);
    	log.info("Student Registration Status: "+isRegistered);
    	if(isRegistered==1)
		{
			log.info("Your Registration is completed. You can't drop courses");
			return;
		}
    	log.info("You added following courses");
    	Map<Integer,String> map = studentOp.getAddedCourses(userId);
    	for(Map.Entry<Integer, String> entry:map.entrySet())
    		log.info(String.format("%25s %25s", entry.getKey(),entry.getValue()));
 	   
       System.out.print("\nEnter course Id: ");
 	   int courseId = scan.nextInt();
 	   try {
 	   int coursePref = studentOp.dropCourses(userId,courseId); 
 	   courses.remove(courseId);
 	   
 	   log.info("Select Course Ids from below given Course Catalog");
 	   studentOp.viewCourseCatalog();
 	   if(coursePref==0) {
 		   log.info("You have to add Complusory course");
 		  courseId = addCompulsoryCourse(1);
 		  studentOp.AddSingleCourse(userId,courseId,0);
 	   }
 	   if(coursePref==1) {
 		   log.info("You have to add Alternative course");
 		   courseId = addAlternativeCourse(1);
 		  studentOp.AddSingleCourse(userId,courseId,1);
 	   }
 	   }
 	   catch(UserNotFoundException e) {
 		   log.info("User with id "+e.getUserId()+" is not found");
 	   } catch(CourseNotFoundException e) {
 		   log.info("Course with id "+e.getCourseId()+" is not found");
 	   }
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
	
	@RequestMapping(value="/student/{id}/viewReportCard",method = RequestMethod.POST)
	public void viewReportCard(@PathVariable int userId)
	{
 	   try {
 	   studentOp.viewReportCard(userId); }      
 	   catch(UserNotApprovedException e) {
 		   log.info("Courses of student with id "+e.getUserId()+" is not approved by admin");
 	   }
	}
	
	@RequestMapping(value="/student/{id}/viewCourseCatalog",method = RequestMethod.POST)
	public void viewCourseCatalog()
	{
		studentOp.viewCourseCatalog(); 
	}
	

}
