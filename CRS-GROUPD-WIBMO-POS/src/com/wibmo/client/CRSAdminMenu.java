/**
 * 
 */
package com.wibmo.client;
import com.wibmo.bean.Admin;
import com.wibmo.bean.User;
import com.wibmo.business.*;
import com.wibmo.validator.ClientValidatorImpl;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;

import java.util.*;
/**
 * Admin Menu Class.
 */
public class CRSAdminMenu {
	AdminOperation adminOp = new AdminOperationImpl();
	public ClientValidatorImpl clientValidator = new ClientValidatorImpl();
	Scanner scan = new Scanner(System.in);
	
	/**
	 * To display the admin registration menu.
	 */
	public void adminRegistration() {
		System.out.println("Enter the Details for Registration");
   		Admin user = new Admin();
   		System.out.print("Enter User ID: ");
   		user.setUserId(scan.nextInt());
   		System.out.print("\nEnter Name: ");
   		user.setUserName(scan.next());
   		System.out.print("\nEnter Email: ");
   		user.setUserEmail(scan.next());
   		String password = clientValidator.passwordValidator();
   		user.setUserPassword(password);
   		System.out.print("\nEnter Phone Number: ");
   		user.setUserPhonenumber(scan.nextLong());
   		try {
    	   adminOp.adminRegistration(user);}
   		catch(UserAlreadyExistsException e){
   			log.info("Admin with AdminId "+e.getUserId()+" alreadyExists");
   		}
	}
	
	
	/**
	 * To display admin registration menu.
	 */
	public void adminMenu() {
		

       boolean flag = false;
        
       while(true) {
    	   log.info("");
    	   log.info("============================ Admin Menu ===========================================");
    	   log.info("");
    	log.info("1. Approve Student Registration");
		
		System.out.println("2. Approve Student's Course Registration");

        System.out.println("3. Register and Approve an Admin");

        log.info("4. Assign courses to Professor");
        
         log.info("5. Exit");
        log.info("");
      	 log.info("===================================================================================");
       log.info("Enter your Choice: ");
        log.info("");
       
        
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
    		   try {
    		   adminOp.approveStudentById();}
    		   catch(UserNotFoundException e) {
    			   log.info("'Student with id "+e.getUserId()+" does not exist'");
    		   }
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
