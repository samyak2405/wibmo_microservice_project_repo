/**
 * 
 */
package com.wibmo.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.wibmo.business.*;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.ClientValidatorImpl;
import com.wibmo.bean.User;

/**
 * Main Class.
 */
public class CRSApplicationClient {
	
	static final StudentOperation studentOp = new StudentOperationImpl();
	public static ClientValidatorImpl clientValidator = new ClientValidatorImpl();
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

        System.out.format("\t%15s%s","\t","Welcome to the CRS Application\n");

        System.out.format("\t%15s\t%s %s %s","\t",localDate.getDayOfMonth(),localDate.getMonth(),localDate.getYear());

        log.info("");

        System.out.format("\t%20s     %s","\t",localTime.format(DateTimeFormatter.ofPattern("HH:mm")));

        

        
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
        		System.out.print("\nEnter your Email: ");
        		String userEmail = scan.next();
        		System.out.print("\nEnter your Password: ");
        		String password = scan.next();
        		
        		
        		//Authentication
        		AuthenticationOperation loggedin=new AuthenticationOperationImpl();
        		
        		if(loggedin.loggedin(userEmail, password,role)) {
        		switch(role) {
        		case 1:
        			log.info("\nYou are logged in successfully as a student");
        			CRSStudentMenu studentMenu = new CRSStudentMenu(userEmail);
        			studentMenu.studentMenu();
        			break;
        		case 2:
        			//Professor
        			log.info("You are logged in successfully as a Professor");
        			CRSProfessorMenu professorMenu = new CRSProfessorMenu(userEmail);
        			professorMenu.professorMenu();
        			break;
        		case 3: 
        			log.info("You are logged in successfully as a Admin");

        			CRSAdminMenu adminMenu = new CRSAdminMenu(userEmail);
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
        		System.out.print("Enter UserEmail:");
        		userEmail = scan.next();
        		System.out.print("\nEnter Current Password: ");
        		password = scan.next();
        		
        		AuthenticationOperation loggedin1=new AuthenticationOperationImpl();
        		if(loggedin1.loggedin(userEmail, password,role)) {
        			System.out.println("\nChange Password");
        			String passwordOne = clientValidator.passwordValidator();
        			loggedin1.updatePassword(userEmail, passwordOne, role);
        		}
        		else {
        			log.info("Invalid credentials");
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
