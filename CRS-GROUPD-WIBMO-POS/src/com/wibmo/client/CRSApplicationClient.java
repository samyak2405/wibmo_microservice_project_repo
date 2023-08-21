/**
 * 
 */
package com.wibmo.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.wibmo.business.*;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.bean.User;

/**
 * 
 */
public class CRSApplicationClient {
	
	static final StudentOperation studentOp = new StudentOperationImpl();

	/**
	 * @param args
	 * @throws DuplicateCourseEntryException 
	 * @throws CourseNotFoundException 
	 * @throws UserNotFoundException 
	 * @throws StudentAlreadyRegisteredException 
	 */
	public static void main(String[] args) throws UserNotFoundException, CourseNotFoundException, DuplicateCourseEntryException, StudentAlreadyRegisteredException {
		// TODO Auto-generated method stub
		LocalDate localDate = LocalDate.now();

        LocalTime localTime = LocalTime.now();

        Scanner scan=new Scanner(System.in);
        final Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
        
        log.info("===================================================================================");

        System.out.format("\t%15s%s","\t","Welcome to the CRS application\n");

        System.out.format("\t%15s\t%s %s %s","\t",localDate.getDayOfMonth(),localDate.getMonth(),localDate.getYear());

        log.info("");

        System.out.format("\t%15s     %s","\t",localTime);

        

        
        int role=-1;
        while(true) {
        	System.out.println();

            System.out.println("===================================================================================");
        	log.info("1. Login"
        			+"\n2. Registration"
        			+"\n3. Update Password"
        			+"\n4. Exit");
        	System.out.println();
        	 System.out.println("===================================================================================");
        	log.info("\nEnter your choice: ");
        	
        	int choice = scan.nextInt();
        	 System.out.println("\n===================================================================================");
        	log.info(
    				"\n1. Student"
    				+"\n2. Professor"
    				+"\n3. Admin");
        	System.out.println();
        	 System.out.println("===================================================================================");
        	 
        	System.out.println("Enter your role");
    		role = scan.nextInt();
    		
        	boolean flag = false;
        	switch(choice) {
        	case 1:
        		log.info("\nEnter your UserId: ");
        		int userId = scan.nextInt();
        		log.info("\nEnter your Password: ");
        		String password = scan.next();
        		
        		
        		//Authentication
        		AuthenticationOperation loggedin=new AuthenticationOperationImpl();
        		
        		if(loggedin.loggedin(userId, password,role)) {
        		switch(role) {
        		case 1:
        			log.info("\nYou are logged in successfully as a student");
        			CRSStudentMenu studentMenu = new CRSStudentMenu(userId);
        			studentMenu.studentMenu();
        			break;
        		case 2:
        			//Professor
        			log.info("You are logged in successfully as a Professor");
        			CRSProfessorMenu professorMenu = new CRSProfessorMenu(userId);
        			professorMenu.professorMenu();
        			break;
        		case 3: 
        			log.info("You are logged in successfully as a Admin");

        			CRSAdminMenu adminMenu = new CRSAdminMenu();
        			adminMenu.adminMenu();
        			break;
        		}
        		}
        		else {
        			log.info("\ninvalid credentials");
        		}
        		break;
        	case 2:
        		if(role==1)
        		{
	        		CRSStudentMenu studentMenu = new CRSStudentMenu();
	    			studentMenu.studentRegistration();
        		}
        		else if(role==2)
        		{
        			CRSProfessorMenu professorMenu = new CRSProfessorMenu();
	    			professorMenu.professorRegistration();
        		}
        		else if(role==3)
        		{
        			CRSAdminMenu adminMenu = new CRSAdminMenu();
	    			adminMenu.adminRegistration();
        		}
        		else {
        			log.info("You have entered an incorrect choice!");
        		}
        		break;
        	case 3:
        		log.info("Enter UserId:");
        		userId = scan.nextInt();
        		log.info("\nEnter Current Password: ");
        		password = scan.next();
        		
        		AuthenticationOperation loggedin1=new AuthenticationOperationImpl();
        		if(loggedin1.loggedin(userId, password,role)) {
        			log.info("\nChange Password");
        			while(true) {
            			log.info("\nEnter New Password: ");
                		String passwordOne = scan.next();
                		log.info("\nEnter Password Again: ");
                		String passwordAgain = scan.next();
                		
                		if(passwordOne.equals(passwordAgain))
                		   {loggedin1.updatePassword(userId, passwordOne, role);
                			break;
                		   }
                		else
                			log.info("Password does not match");
            		}
        		}
        		else {
        			log.info("invalid credentials");
        			System.out.println();
               	    System.out.println("===================================================================================");
        			
        		}
        		
        	break;
        	case 4: flag = true;
        		break;
        	}
        	if(flag)
        		break;
        }
	}

}
