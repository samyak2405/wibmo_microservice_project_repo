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
 * 
 */
public class CRSStudentMenu {
	StudentOperation studentOp = new StudentOperationImpl();
	NotificationOperation notificationOp=new NotificationOperationImpl();
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
	
	public void addCourses() throws DuplicateCourseEntryException {
 	   
 	   studentOp.viewCourseCatalog();
 	   
 	   System.out.print("Enter your Id: ");
 	   StudentCourseMap studCoMap = new StudentCourseMap();
 	   studCoMap.setStudentId(scan.nextInt());
 	   Map<Integer,Integer> courses = new HashMap<>();
 	   
 	   while(cnt>0) {
 		   System.out.print("\nEnter the Course Id: ");
     	   int courseId = scan.nextInt();
     	   if(courses.containsKey(courseId))
     	   {
     		   System.out.println("You entered duplicate choice. Please add another Course");
     		   continue;
     	   }
     	   System.out.print("\nEnter the Course Preference: ");
     	   int coursePref = scan.nextInt();
     	   if(coursePref==0 && compulsory==0)
     	   {
     		   System.out.println("You have entered 4 complusory. Please add the alternatives");
     		   continue;
     	   }
     	   if(coursePref==1 && alternative==0)
     	   {
     		   System.out.println("You have entered 2 alternatives. Please add the compulsory");
     		   continue;
     	   }
     	   System.out.println();
     	   
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
	} catch (CourseNotFoundException e) {
		System.out.println("Course with course id: "+e.getCourseId()+" Not Found!!");
	}catch(CourseLimitExceededException e)
 	   {
		System.out.println("Course Limit Exceeded");
 	   }
 	   System.out.println("Course Added Successfully.");
	}
	
	
	
	/**
	 * 
	 * @throws StudentAlreadyRegisteredException
	 */
	public void studentRegistration() throws StudentAlreadyRegisteredException  {
		
		System.out.println("Enter the Details for Registration");
		User user = new User();
		System.out.print("Enter User ID: ");
		user.setUserId(scan.nextInt());
		System.out.print("\nEnter Name: ");
		user.setUserName(scan.next());
		System.out.print("\nEnter Email: ");
		user.setUserEmail(scan.next());
		while(true) {
			System.out.print("\nEnter Password: ");
    		String passwordOne = scan.next();
    		System.out.print("\nEnter Password Again: ");
    		String passwordAgain = scan.next();
    		if(passwordOne.equals(passwordAgain))
    		{
    			user.setUserPassword(passwordOne);
    			break;
    		}
    		else
    			System.out.println("Password does not match");
		}
		
		System.out.print("\nEnter Phone Number: ");
		user.setUserPhonenumber(scan.nextLong());
		try {
		studentOp.registerStudent(user);}
		catch(StudentAlreadyRegisteredException e) {
			System.out.println("student with id "+e.getStudentId()+"is already registered");
		}
	}
	
	
	public void studentMenu() throws DuplicateCourseEntryException, CourseNotFoundException, UserNotFoundException {

       boolean flag = false;
        
       while(true) {
   		System.out.print("\nChoose From below given list"
   				+"\n\n1.Register for course"
   				+"\n2.Add course"
   				+"\n3.Drop course"
   				+"\n4.view list of Registered Courses"
   				+"\n5.view ReportCard"
   				+"\n6.viewCourseCatalog"
   				+"\n7.Pay Fee"
   				+"\n8.View Notification"
   				+"\n9.Exit\n");
    	System.out.print("Enter your Choice: ");
    	int opt=scan.nextInt();
       switch(opt) {
       case 1:
    	   studentOp.registerCourses(userId);
    	   break;
       case 2:
    	   System.out.println("Select Course Ids from below given Course Catalog");
     	   System.out.print("You have to select "+compulsory+" complusory and "+alternative+" Alternative");
     	   System.out.println("\nFor complusory enter 0 and for alternative enter 1");
     	   
    	   addCourses();
        break;

       case 3:
    	   System.out.println("Enter course id");
    	   int courseId = scan.nextInt();
    	   try {
    	   int coursePref = studentOp.dropCourses(userId,courseId);    	  
    	   if(coursePref==0)
    		   compulsory++;
    	   if(coursePref==1)
    		   alternative++;
    	   System.out.println("Select Course Ids from below given Course Catalog");
     	   System.out.print("You have to select "+compulsory+" complusory and "+alternative+" Alternative");
     	   System.out.println("\nFor complusory enter 0 and for alternative enter 1");
    	   addCourses(); }
    	   catch(UserNotFoundException e) {
    		   System.out.println("User with id "+e.getUserId()+" is not found");
    	   } catch(CourseNotFoundException e) {
    		   System.out.println("Course with id "+e.getCourseId()+" is not found");
    	   }

        break;

       case 4:
    	   try {
    	   studentOp.listCourse(userId);}
    	   catch(UserNotApprovedException e) {
    		   System.out.println("User with id "+e.getUserId()+" is not approved by admin");
    	   }
        break;

       case 5:
//    	   System.out.print("Enter your Id: ");
//    	   int studId = scan.nextInt();
    	   try {
    	   studentOp.viewReportCard(userId); }      
    	   catch(UserNotApprovedException e) {
    		   System.out.println("Courses of student with id "+e.getUserId()+" is not approved by admin");
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
    		   System.out.println("Student courses are not approved by admin");
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
