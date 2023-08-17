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
		System.out.println("1. Approve Course Requests from students");

        System.out.println("2. Register and Approve an Admin");

        System.out.println("3. Assign courses to Professor");
        
        System.out.println("4. Exit");

       boolean flag = false;
        
       while(true) {
    	System.out.print("Enter your Choice: ");
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	  adminOp.approveStudent();
        break;
       case 2:
    	   adminRegistration();
    	   break;
       case 3:
    	   adminOp.assignCoursesProf();
        break;
       case 4: flag = true;
       break;
       }
       if(flag)
    	   break;
      }
	}
}
