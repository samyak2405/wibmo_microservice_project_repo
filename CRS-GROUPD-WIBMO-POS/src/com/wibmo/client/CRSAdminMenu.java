/**
 * 
 */
package com.wibmo.client;
import com.wibmo.bean.Admin;
import com.wibmo.bean.User;
import com.wibmo.business.*;
import java.util.*;
/**
 * 
 */
public class CRSAdminMenu {
	AdminOperation adminOp = new AdminOperationImpl();
	Scanner scan = new Scanner(System.in);
	
	
	public void adminRegistration() {
		System.out.println("Enter the Details for Registration");
   		Admin user = new Admin();
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
    	   adminOp.adminRegistration(user);
	}
	
	public void adminMenu() {
		System.out.println("1. Approve Student Registration");
		
		System.out.println("2. Approve Student's Course Registration");

        System.out.println("3. Register and Approve an Admin");

        System.out.println("4. Assign courses to Professor");
        
        System.out.println("5. Exit");

       boolean flag = false;
        
       while(true) {
    	System.out.print("Enter your Choice: ");
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	   System.out.println("a. Approve all students at once"
    			   +"\nb. Approve Student by Id");
    	   System.out.print("\nEnter your Choice: ");
    	   char choice = scan.next().charAt(0);
    	   switch(choice) {
    	   case 'a':
    	    	  adminOp.approveStudent();
    		   break;
    	   case 'b':
    		   adminOp.approveStudentById();
    		   break;
    	   }
        break;
       case 2:
    	   adminOp.approveCourseRegistration();
    	   break;
       case 3:
    	   adminRegistration();
    	   break;
       case 4:
    	   adminOp.assignCoursesProf();
        break;
       case 5: flag = true;
       break;
       }
       if(flag)
    	   break;
      }
	}
}
