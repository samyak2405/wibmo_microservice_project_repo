/**
 * 
 */
package com.wibmo.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.wibmo.bean.ProfessorCourseMap;
import com.wibmo.bean.User;
import com.wibmo.business.*;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.ClientValidatorImpl;
/**
 * Professor Menu Class.
 */
public class CRSProfessorMenu {

	private ProfessorOperation professorOp = new ProfessorOperationImpl();
	public ClientValidatorImpl clientValidator = new ClientValidatorImpl();
	String userEmail;
	final static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	
	public CRSProfessorMenu()
	{
		
	}
	
	
	public CRSProfessorMenu(String userEmail)
	{
		this.userEmail=userEmail;
	}
	
	
	Scanner scan = new Scanner(System.in);
	/**
	 * To display professor choice menu
	 * @throws CourseNotFoundException 
	 * @throws UserNotFoundException 
	 */
	public void professorMenu() {

       boolean flag = false;
        
       while(true) {
   		System.out.println();

        System.out.println("===================================================================================");
		   log.info("1.Request Course Offering");


        log.info("2.View Student List");

        log.info("3.View Course Catalog");

        log.info("4.Set Grades");

        log.info("5.Exit");
		System.out.println();

        System.out.println("===================================================================================");

    	System.out.println("Enter your Choice: ");
    	System.out.println();
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	   ProfessorCourseMap profcomap=new ProfessorCourseMap();
           professorOp.viewCourseCatalog();
//           log.info("1.Request Course");
//           log.info("2.Exit");
            boolean flag1=false;

            List<Integer> courseIdList =new ArrayList<>();
               while(true) {
                   System.out.println();

        System.out.println("===================================================================================");
				   log.info("1.Request Course");
                   log.info("2.Freeze list");
				   System.out.println();

        System.out.println("===================================================================================");
               System.out.print("Enter your Choice: ");
                int opt1=scan.nextInt();
               switch(opt1) {

               case 1:
                System.out.print("Enter courseid: ");
                int courseid1=scan.nextInt();
                courseIdList.add(courseid1);
                break;

               case 2:
                  flag1=true;
                   try {
                	int userId = professorOp.getProfessorById(userEmail);
					professorOp.requestCourseOffering(userId,courseIdList);
				} catch (CourseNotFoundException e) {
					log.info("Course with course id:"+e.getCourseId()+" Not Found");
				}
                  break;
              }

            if(flag1)

             break;

              }
        break;

       case 2:
    	   log.info("Enter courseid: ");
           int courseid=scan.nextInt();
           try {
			professorOp.viewStudentList(courseid);
		} catch (CourseNotFoundException e) {
			log.info("Course with course id:"+e.getCourseId()+" Not Found");

		}
        break;

       case 3:
    	   professorOp.viewCourseCatalog();
        break;

       case 4:
    	   	   log.info("Enter the StudentId");
	    	   int studentId=scan.nextInt();
	    	   
	    	   log.info("Course Id");
	    	   int courseId=scan.nextInt();
	    	   
	    	   log.info("Enter grades");
	    	   String grade=scan.next();
		try {
			professorOp.setGrades(studentId,courseId,grade);
		} catch (UserNotFoundException e) {
			log.info("Student with id:"+e.getUserId()+" Not Found");
			
		}catch(CourseNotFoundException e)
		{
			log.info("Course with id:"+e.getCourseId()+" Not Found");
		}
        break;
        
       case 5: flag = true;
       break;
       }
       if(flag)
    	   break;
      }
		
	}
	
	/**
	 * To display the professor Registration menu.
	 */
	public void professorRegistration() {
		// TODO Auto-generated method stub
		log.info("Enter the Details for Registration");
		User user = new User();
		System.out.print("\nEnter User ID: ");
		user.setUserId(scan.nextInt());
		System.out.print("\nEnter Name: ");
		user.setUserName(scan.next());
		System.out.print("\nEnter Email: ");
		user.setUserEmail(scan.next());
		String password = clientValidator.passwordValidator();
		user.setUserPassword(password);
		
		System.out.print("\nEnter Phone Number: ");
		System.out.println();
      	 System.out.println("===================================================================================");
		user.setUserPhonenumber(scan.nextLong());
		try {
			professorOp.registerProfessor(user);
		} catch (UserAlreadyExistsException e) {
			log.info("User with user id:"+e.getUserId()+" Already Exists");

		}
		
	}
}
