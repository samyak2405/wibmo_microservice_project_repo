/**
 * 
 */
package com.wibmo.client;
import com.wibmo.business.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wibmo.bean.StudentCourseMap;
import com.wibmo.bean.User;
import com.wibmo.business.*;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;

/**
 * Student menu class.
 */
public class CRSStudentMenu {
	
	StudentOperation studentOp = new StudentOperationImpl();
	NotificationOperation notificationOp=new NotificationOperationImpl();
	final static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	Scanner scan = new Scanner(System.in);
	int cnt;
	int compulsory;
	int alternative;
	int userId;
	
	public CRSStudentMenu() {
		cnt = 6;
		compulsory = 4;
		alternative = 2;
	}
	
	public CRSStudentMenu(int userId) {
		cnt = 6;
		compulsory = 4;
		alternative = 2;
		this.userId = userId;
	}
	
	
	/**
	 * To display the menu for adding new courses.
	 * @throws DuplicateCourseEntryException
	 */
	public void addCourses() throws DuplicateCourseEntryException {
 	   
 	   studentOp.viewCourseCatalog();
 	  System.out.println();
    	 System.out.println("===================================================================================");
 	   
 	   log.info("Enter your Id: ");
 	   StudentCourseMap studCoMap = new StudentCourseMap();
 	   studCoMap.setStudentId(userId);
 	   Map<Integer,Integer> courses = new HashMap<>();
 	
 	   while(cnt>0) {
 		   log.info("\nEnter the Course Id: ");
     	   int courseId = scan.nextInt();
     	   if(courses.containsKey(courseId))
     	   {
     		   log.info("You entered duplicate choice. Please add another Course");
     		   continue;
     	   }
     	   log.info("\nEnter the Course Preference: ");
     	   int coursePref = scan.nextInt();
     	   if(coursePref==0 && compulsory==0)
     	   {
     		   log.info("You have entered 4 complusory. Please add the alternatives");
     		   continue;
     	   }
     	   if(coursePref==1 && alternative==0)
     	   {
     		   log.info("You have entered 2 alternatives. Please add the compulsory");
     		   continue;
     	   }
     	   log.info("");
     	   
     		   if(coursePref==0)
     			   	compulsory--;
     		   if(coursePref==1)
     			   alternative--;
     		   courses.put(courseId, coursePref);
         	   cnt--;
 	   }
 	   studCoMap.setCourses(courses);
 	   try {
		studentOp.addCourses(studCoMap);
		System.out.println("Course Added Successfully.");
	} catch (CourseNotFoundException e) {
		log.info("Course with course id: "+e.getCourseId()+" Not Found!!");
	}catch(CourseLimitExceededException e)
 	   {
		log.info("Course Limit Exceeded");
 	   }
 	   log.info("Course Added Successfully.");
	}
	
	
	
	/**
	 * To display the student registration menu.
	 * @throws StudentAlreadyRegisteredException
	 */
	public void studentRegistration() throws StudentAlreadyRegisteredException  {
		
		log.info("Enter the Details for Registration");
		User user = new User();
		log.info("Enter User ID: ");
		user.setUserId(scan.nextInt());
		log.info("\nEnter Name: ");
		user.setUserName(scan.next());
		log.info("\nEnter Email: ");
		user.setUserEmail(scan.next());
		while(true) {
			log.info("\nEnter Password: ");
    		String passwordOne = scan.next();
    		log.info("\nEnter Password Again: ");
    		String passwordAgain = scan.next();
    		if(passwordOne.equals(passwordAgain))
    		{
    			user.setUserPassword(passwordOne);
    			break;
    		}
    		else
    			log.info("Password does not match");
		}
		
		log.info("\nEnter Phone Number: ");
		user.setUserPhonenumber(scan.nextLong());
//		System.out.println();
//      	 System.out.println("===================================================================================");
		try {
		studentOp.registerStudent(user);}
		catch(StudentAlreadyRegisteredException e) {
			log.info("student with id "+e.getStudentId()+"is already registered");
		}
	}
	
	
	
	/**
	 * To display the student choice menu.
	 * @throws DuplicateCourseEntryException
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException
	 */
	public void studentMenu() throws DuplicateCourseEntryException, CourseNotFoundException, UserNotFoundException {

       boolean flag = false;
        
       while(true) {
   		System.out.println();

        System.out.println("===================================================================================");
		   log.info("\nChoose From below given list"
   				+"\n\n1.Register for course"
   				+"\n2.Add course"
   				+"\n3.Drop course"
   				+"\n4.view list of Registered Courses"
   				+"\n5.view ReportCard"
   				+"\n6.viewCourseCatalog"
   				+"\n7.Pay Fee"
   				+"\n8.View Notification"
   				+"\n9.Exit\n");
    	System.out.println();

        System.out.println("===================================================================================");
		log.info("Enter your Choice: ");
    	int opt=scan.nextInt();
       switch(opt) {
       case 1:
    	   studentOp.registerCourses(userId);
    	   break;
       case 2:
    	   System.out.println("\nSelect Course Ids from below given Course Catalog");
     	   System.out.println("\nYou have to select "+compulsory+" complusory and "+alternative+" Alternative(For complusory enter 0 and for alternative enter 1)");
     	  System.out.println();
        	 System.out.println("===================================================================================");
//     	   System.out.println("\nFor complusory enter 0 and for alternative enter 1\n");
     	   
    	   addCourses();
        break;

       case 3:
    	   log.info("Enter course id");
    	   int courseId = scan.nextInt();
    	   try {
    	   int coursePref = studentOp.dropCourses(userId,courseId);    	  
    	   if(coursePref==0)
    		   compulsory++;
    	   if(coursePref==1)
    		   alternative++;
    	   log.info("Select Course Ids from below given Course Catalog");
     	   log.info("You have to select "+compulsory+" complusory and "+alternative+" Alternative");
     	   log.info("\nFor complusory enter 0 and for alternative enter 1");
    	   addCourses(); }
    	   catch(UserNotFoundException e) {
    		   log.info("User with id "+e.getUserId()+" is not found");
    	   } catch(CourseNotFoundException e) {
    		   log.info("Course with id "+e.getCourseId()+" is not found");
    	   }

        break;

       case 4:
    	   try {
    	   studentOp.listCourse(userId);}
    	   catch(UserNotApprovedException e) {
    		   log.info("User with id "+e.getUserId()+" is not approved by admin");
    	   }
        break;

       case 5:
//    	   log.info("Enter your Id: ");
//    	   int studId = scan.nextInt();
    	   try {
    	   studentOp.viewReportCard(userId); }      
    	   catch(UserNotApprovedException e) {
    		   log.info("Courses of student with id "+e.getUserId()+" is not approved by admin");
    	   }
        break;

       case 6:
    	   studentOp.viewCourseCatalog();      
		break;
		
       case 7:
    	   
    	   CRSPaymentMenu payment=new CRSPaymentMenu();
    	   
    	   if(studentOp.isApproved(userId)) {
    	   payment.payfee(userId);}
    	   else {
    		   log.info("Student courses are not approved by admin");
    	   }
		break;
       case 8:
    	   notificationOp.getNotificationMessage(userId);
		break;
       case 9: flag = true;
       break;
       }
       if(flag)
    	   break;
      }
	}
}
