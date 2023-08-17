/**
 * 
 */
package com.wibmo.client;
import com.wibmo.business.*;
import java.util.*;
/**
 * 
 */
public class CRSAdminMenu {
	AdminOperation adminOp = new AdminOperationImpl();
	Scanner scan = new Scanner(System.in);
	public void adminMenu() {
		System.out.println("1. Approve Student");

        System.out.println("2. Add Admin");

        System.out.println("3. Assign courses to Professor");

        System.out.println("4. Add Course");

        System.out.println("5. Drop Course");
        
        System.out.println("6.Exit");

       boolean flag = false;
        
       while(true) {
    	System.out.print("Enter your Choice: ");
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	  adminOp.approveStudent(opt, null);
        break;

       case 2:
    	   adminOp.addAdmin(null);
        break;

       case 3:
    	   adminOp.assignCoursesProf(opt, opt);
        break;

       case 4:
    	   adminOp.addCourse(opt);
        break;
        
       case 5:
    	   adminOp.dropCourse(opt);
        break;
           
       case 6: flag = true;
       break;
       }
       if(flag)
    	   break;
      }
	}
}
