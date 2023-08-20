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
import com.wibmo.validator.ClientValidatorImpl;
/**
 * 
 */
public class CRSProfessorMenu {

	private ProfessorOperation professorOp = new ProfessorOperationImpl();
	public ClientValidatorImpl clientValidator = new ClientValidatorImpl();
	String userEmail;

	
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
   		System.out.println("1.Request Course Offering");

        System.out.println("2.View Student List");

        System.out.println("3.View Course Catalog");

        System.out.println("4.Set Grades");

        System.out.println("5.Exit");

    	System.out.print("Enter your Choice: ");
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	   ProfessorCourseMap profcomap=new ProfessorCourseMap();
           professorOp.viewCourseCatalog();
//           System.out.println("1.Request Course");
//           System.out.println("2.Exit");
            boolean flag1=false;

            List<Integer> courseIdList =new ArrayList<>();
               while(true) {
                   System.out.println("1.Request Course");
                   System.out.println("2.Freeze list");
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
	    	   int studentId=scan.nextInt();
	    	   
	    	   System.out.println("Course Id");
	    	   int courseId=scan.nextInt();
	    	   
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
		System.out.print("\nEnter Name: ");
		user.setUserName(scan.next());
		System.out.print("\nEnter Email: ");
		user.setUserEmail(scan.next());
		String password = clientValidator.passwordValidator();
		user.setUserPassword(password);
		
		System.out.print("\nEnter Phone Number: ");
		user.setUserPhonenumber(scan.nextLong());
		try {
			professorOp.registerProfessor(user);
		} catch (UserAlreadyExistsException e) {
			System.out.println("User with user id:"+e.getUserId()+" Already Exists");

		}
		
	}
}
