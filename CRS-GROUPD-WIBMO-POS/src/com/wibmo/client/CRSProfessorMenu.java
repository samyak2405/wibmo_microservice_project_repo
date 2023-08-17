/**
 * 
 */
package com.wibmo.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.wibmo.bean.ProfessorCourseMap;

import com.wibmo.business.*;
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

            List<Long> courseIdList =new ArrayList<>();
               while(true) {
                   System.out.println("1.Request Course");
                   System.out.println("2.Freeze list");
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
                   professorOp.requestCourseOffering(userId,courseIdList);
                  break;
              }

            if(flag1)

             break;

              }
        break;

       case 2:
    	   System.out.println("Enter courseid: ");
           int courseid=scan.nextInt();
           professorOp.viewStudentList(courseid);
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
	    	   professorOp.setGrades(studentId,courseId,grade);
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
		
	}
}
