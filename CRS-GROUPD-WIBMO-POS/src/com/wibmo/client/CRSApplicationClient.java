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
import com.wibmo.business.*;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.ClientValidatorImpl;
import com.wibmo.bean.User;

/**
 * 
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

        System.out.println("===================================================================================");

        System.out.format("\t%15s%s","\t","Welcome to the CRS Application\n");

        System.out.format("\t%15s\t%s %s %s","\t",localDate.getDayOfMonth(),localDate.getMonth(),localDate.getYear());

        System.out.println();

        System.out.format("\t%20s     %s","\t",localTime.format(DateTimeFormatter.ofPattern("HH:mm")));

        

        System.out.println();

        System.out.println("===================================================================================");
        int role=-1;
        while(true) {
        	System.out.println("1. Login"
        			+"\n2. Registration"
        			+"\n3. Update Password"
        			+"\n4. Exit");
        	System.out.print("\nEnter your choice: ");
        	int choice = scan.nextInt();
        	
        	System.out.print("\nEnter your Role "
    				+"\n1. Student"
    				+"\n2. Professor"
    				+"\n3. Admin");
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
        			System.out.println("\nYou are logged in successfully as a student");
        			CRSStudentMenu studentMenu = new CRSStudentMenu(userEmail);
        			studentMenu.studentMenu();
        			break;
        		case 2:
        			//Professor
        			System.out.println("You are logged in successfully as a Professor");
        			CRSProfessorMenu professorMenu = new CRSProfessorMenu(userEmail);
        			professorMenu.professorMenu();
        			break;
        		case 3: 
        			System.out.println("You are logged in successfully as a Admin");

        			CRSAdminMenu adminMenu = new CRSAdminMenu();
        			adminMenu.adminMenu();
        			break;
        		}
        		}
        		else {
        			System.out.println("\nInvalid credentials");
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
        			System.out.println("You have entered an incorrect choice!");
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
        			System.out.println("invalid credentials");
        			
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
