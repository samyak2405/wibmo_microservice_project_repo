/**
 * 
 */
package com.wibmo.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.wibmo.business.*;
/**
 * 
 */
public class CRSProfessorMenu {
	ProffesorOperation proffesorOp = new ProffesorOperationImpl();
	Scanner scan = new Scanner(System.in);
	public void professorMenu() {
		System.out.println("1.Request Course Offering");

        System.out.println("2.View Student List");

        System.out.println("3.View Course Catalog");

        System.out.println("4.Set Grades");

        System.out.println("5.Exit");

       boolean flag = false;
        
       while(true) {
    	System.out.print("Enter your Choice: ");
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	   proffesorOp.requestCourseOffering(null);
        break;

       case 2:
    	   proffesorOp.viewStudentList(0);
        break;

       case 3:
    	   proffesorOp.viewCourseCatalog();
        break;

       case 4:
    	   proffesorOp.setGrades(0, 0);
        break;
        
       case 5: flag = true;
       break;
       }
       if(flag)
    	   break;
      }
		
	}
}
