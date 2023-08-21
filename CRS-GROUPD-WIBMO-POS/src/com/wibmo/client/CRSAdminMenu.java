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

import org.apache.log4j.Logger;
/**
 * Admin Menu Class
 */
public class CRSAdminMenu {
	AdminOperation adminOp = new AdminOperationImpl();
	public ClientValidatorImpl clientValidator = new ClientValidatorImpl();
	Scanner scan = new Scanner(System.in);
	
	/**
	 * To display the admin registration menu.
	 */
	static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	
	public void adminRegistration() {
		log.info("Enter the Details for Registration");
   		Admin user = new Admin();
   		log.info("Enter User ID: ");
   		user.setUserId(scan.nextInt());
   		log.info("\nEnter Name: ");
   		user.setUserName(scan.next());
   		log.info("\nEnter Email: ");
   		user.setUserEmail(scan.next());
   		String password = clientValidator.passwordValidator();
   		user.setUserPassword(password);
   		System.out.print("\nEnter Phone Number: ");
   		user.setUserPhonenumber(scan.nextLong());
   		try {
    	   adminOp.adminRegistration(user);}
   		catch(UserAlreadyExistsException e){
   			System.out.println("Admin with AdminId "+e.getUserId()+" alreadyExists");
   		}
	}
	
	
	/**
	 * To display admin registration menu.
	 */
	public void adminMenu() {
		

       boolean flag = false;
        
       while(true) {
    	   System.out.println();
         	 System.out.println("===================================================================================");
    	
    	log.info("1. Approve Student Registration");
		
		log.info("2. Approve Student's Course Registration");

        log.info("3. Register and Approve an Admin");

        log.info("4. Assign courses to Professor");
        
         log.info("5. Exit");
        System.out.println();
      	 System.out.println("===================================================================================");
       log.info("Enter your Choice: ");
        System.out.println();
       
        
    	int opt=scan.nextInt();
       switch(opt) {

       case 1:
    	   log.info("a. Approve all students at once"
    			   +"\nb. Approve Student by Id");
    	   log.info("\nEnter your Choice: ");
    	   char choice = scan.next().charAt(0);
    	   switch(choice) {
    	   case 'a':
    	    	  adminOp.approveStudent();
    		   break;
    	   case 'b':
    		   try {
    		   adminOp.approveStudentById();}
    		   catch(UserNotFoundException e) {
    			   System.out.println("'Student with id "+e.getUserId()+" does not exist'");
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
