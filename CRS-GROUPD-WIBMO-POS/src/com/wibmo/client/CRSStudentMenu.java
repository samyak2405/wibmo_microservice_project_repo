/**
 * 
 */
package com.wibmo.client;
import com.wibmo.business.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wibmo.bean.StudentCourseMap;
import com.wibmo.bean.User;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.ClientValidatorImpl;

/**
 * Student menu class.
 */
public class CRSStudentMenu {
	private String userEmail;
	private int userId;
	Map<Integer,Integer> courses;
	public StudentOperation studentOp = new StudentOperationImpl();
	public NotificationOperation notificationOp=new NotificationOperationImpl();
	public ClientValidatorImpl clientValidator = new ClientValidatorImpl();
	final static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	Scanner scan = new Scanner(System.in);
	
	
	public CRSStudentMenu() {
		
	}
	
	public CRSStudentMenu(String userEmail) {
		this.userEmail = userEmail;
		userId = studentOp.getStudentByEmail(userEmail);  
		courses = new HashMap<>();
	}
	
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
	
	
	/**
	 * To display the menu for adding new courses.
	 * @throws DuplicateCourseEntryException
	 */
	public void addCourses() throws DuplicateCourseEntryException {
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
	
	
	
	/**
	 * To display the student registration menu.
	 * @throws StudentAlreadyRegisteredException
	 */
	public void studentRegistration() throws StudentAlreadyRegisteredException  {
		
		log.info("Enter the Details for Registration");
		User user = new User();
		System.out.print("\nEnter Name: ");
		user.setUserName(scan.next());
		log.info("\nEnter Email: ");
		user.setUserEmail(scan.next());
		String password = clientValidator.passwordValidator();
		user.setUserPassword(password);
		System.out.print("\nEnter Phone Number: ");
		user.setUserPhonenumber(scan.nextLong());
//		log.info();
//      	 log.info("===================================================================================");
		try {
		studentOp.registerStudent(user);}
		catch(StudentAlreadyRegisteredException e) {
			log.info("student with id "+e.getStudentEmail()+"is already registered");
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
   		log.info("");
    	 log.info("============================ Student Menu =========================================");
    	 log.info("");
   		System.out.print("\nChoose From below given list"
   				+"\n1. Add course"
   				+"\n2. Drop course"
   				+"\n3. Register for courses"
   				+"\n4. View list of Registered Courses"
   				+"\n5. View ReportCard"
   				+"\n6. ViewCourseCatalog"
   				+"\n7. Pay Fee"
   				+"\n8. View Notification"
   				+"\n9. Exit\n\n");
		log.info("");
 	    log.info("===================================================================================");
   		
    	System.out.print("Enter your Choice: ");
    	int studentChoice=scan.nextInt();
       
    	switch(studentChoice) {
    	case 1:
    		int isRegistered = studentOp.isStudentRegistered(userId);
    		log.info("Student Registration Status: "+isRegistered);
    		if(isRegistered==1)
    		{
    			log.info("Your Registration is completed. You can't add courses");
    			break;
    		}
     	   log.info("\nSelect Course Ids from below given Course Catalog");
      	   System.out.print("\nYou have to select 4 complusory and 2 Alternative");
      	   log.info("\nFor complusory enter 0 and for alternative enter 1");
      	   
     	   addCourses();
         break;

        case 2:
        	isRegistered = studentOp.isStudentRegistered(userId);
        	log.info("Student Registration Status: "+isRegistered);
        	if(isRegistered==1)
    		{
    			log.info("Your Registration is completed. You can't drop courses");
    			break;
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

         break;
       case 3:
    	   studentOp.registerCourses(userId);
    	   
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
    	   payment.payfee(userId);
    	   }
    	   else {
    		   log.info("Student courses are not approved by admin");
    	   }
		break;
       case 8:
    	   userId = studentOp.getStudentByEmail(userEmail);
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
