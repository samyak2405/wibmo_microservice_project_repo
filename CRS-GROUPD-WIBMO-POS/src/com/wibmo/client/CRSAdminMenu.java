/**
 * 
 */
package com.wibmo.client;
import com.wibmo.bean.Admin;
import com.wibmo.bean.User;
import com.wibmo.business.*;
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
   		log.info("Enter User ID: ");
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
   		
   		log.info("\nEnter Phone Number: ");
   		user.setUserPhonenumber(scan.nextLong());
    	   adminOp.adminRegistration(user);
	}
	
	public void adminMenu() {
		

       boolean flag = false;
        
       while(true) {
    	
    	log.info("1. Approve Student Registration");
		
		log.info("2. Approve Student's Course Registration");

        log.info("3. Register and Approve an Admin");

        log.info("4. Assign courses to Professor");
        
        log.info("5. Exit");
        log.info("Enter your Choice: ");
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
