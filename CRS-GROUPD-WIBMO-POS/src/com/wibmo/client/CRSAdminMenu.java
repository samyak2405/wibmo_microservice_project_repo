/**
 * 
 */
package com.wibmo.client;
import com.wibmo.bean.Admin;
import com.wibmo.bean.User;
import com.wibmo.business.*;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;

import java.util.*;

import org.apache.log4j.Logger;
/**
 * 
 */
public class CRSAdminMenu {
	AdminOperation adminOp = new AdminOperationImpl();
	Scanner scan = new Scanner(System.in);
	
	static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	
	public void adminRegistration() {
		log.info("Enter the Details for Registration");
   		Admin user = new Admin();
<<<<<<< HEAD
   		System.out.print("\nEnter User ID: ");
=======
   		log.info("Enter User ID: ");
>>>>>>> be5324df26462c8acfe2e05d1b2534baa92e2a3d
   		user.setUserId(scan.nextInt());
   		log.info("\nEnter Name: ");
   		user.setUserName(scan.next());
   		log.info("\nEnter Email: ");
   		user.setUserEmail(scan.next());
   		while(true) {
   			log.info("\nEnter Password: ");
       		String passwordOne = scan.next();
       		log.info("\nEnter Password Again: ");
       		String passwordAgain = scan.next();
       		if(passwordOne.equals(passwordAgain))
       		{
       			user.setUserPassword(passwordOne);
       			break;
       		}
       		else
       			log.info("Password does not match");
   		}
   		
<<<<<<< HEAD
   		System.out.print("\nEnter Phone Number: ");
   		System.out.println();
      	 System.out.println("===================================================================================");
=======
   		log.info("\nEnter Phone Number: ");
>>>>>>> be5324df26462c8acfe2e05d1b2534baa92e2a3d
   		user.setUserPhonenumber(scan.nextLong());
   		try {
    	   adminOp.adminRegistration(user);}
   		catch(UserAlreadyExistsException e){
   			System.out.println("Admin with AdminId "+e.getUserId()+" alreadyExists");
   		}
	}
	
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
