/**
 * 
 */
package com.wibmo.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.wibmo.bean.ProfessorCourseMap;
import com.wibmo.bean.User;
import com.wibmo.business.*;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
/**
 * 
 */
public class CRSProfessorMenu {

	private ProfessorOperation professorOp = new ProfessorOperationImpl();
	int userId;

	
	public CRSProfessorMenu()
	{
		
	}
	
	
	public CRSProfessorMenu(int userId)
	{
		this.userId=userId;
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
   		System.out.println("1.Request Course Offering");


        System.out.println("2.View Student List");

        System.out.println("3.View Course Catalog");

        System.out.println("4.Set Grades");

        System.out.println("5.Exit");
        System.out.println();
      	 System.out.println("===================================================================================");

    	System.out.println("Enter your Choice: ");
    	System.out.println();
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	   ProfessorCourseMap profcomap=new ProfessorCourseMap();
           professorOp.viewCourseCatalog();
//           System.out.println("1.Request Course");
//           System.out.println("2.Exit");
            boolean flag1=false;

            List<Long> courseIdList =new ArrayList<>();
               while(true) {
            	   System.out.println();

                   System.out.println("===================================================================================");
                   System.out.println("1.Request Course");
                   System.out.println("2.Freeze list");
                   System.out.println();

                   System.out.println("===================================================================================");
               System.out.print("Enter your Choice: ");
                int opt1=scan.nextInt();
               switch(opt1) {

               case 1:
                System.out.print("Enter courseid: ");
                long courseid1=scan.nextLong();
                courseIdList.add(courseid1);
                break;

               case 2:
                  flag1=true;
                   try {
					professorOp.requestCourseOffering(userId,courseIdList);
				} catch (CourseNotFoundException e) {
					System.out.println("Course with course id:"+e.getCourseId()+" Not Found");
				}
                  break;
              }

            if(flag1)

             break;

              }
        break;

       case 2:
    	   System.out.println("Enter courseid: ");
           int courseid=scan.nextInt();
           try {
			professorOp.viewStudentList(courseid);
		} catch (CourseNotFoundException e) {
			System.out.println("Course with course id:"+e.getCourseId()+" Not Found");

		}
        break;

       case 3:
    	   professorOp.viewCourseCatalog();
        break;

       case 4:
    	   	   System.out.println("Enter the StudentId");
	    	   long studentId=scan.nextLong();
	    	   System.out.println("Course Id");
	    	   long courseId=scan.nextLong();
	    	   System.out.println("Enter grades");
	    	   String grade=scan.next();
		try {
			professorOp.setGrades(studentId,courseId,grade);
		} catch (UserNotFoundException e) {
			System.out.println("Student with id:"+e.getUserId()+" Not Found");
			
		}catch(CourseNotFoundException e)
		{
			System.out.println("Course with id:"+e.getCourseId()+" Not Found");
		}
        break;
        
       case 5: flag = true;
       break;
       }
       if(flag)
    	   break;
      }
		
	}
	public void professorRegistration() {
		// TODO Auto-generated method stub
		System.out.println("Enter the Details for Registration");
		User user = new User();
		System.out.print("\nEnter User ID: ");
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
		System.out.println();
      	 System.out.println("===================================================================================");
		user.setUserPhonenumber(scan.nextLong());
		try {
			professorOp.registerProfessor(user);
		} catch (UserAlreadyExistsException e) {
			System.out.println("User with user id:"+e.getUserId()+" Already Exists");

		}
		
	}
}
