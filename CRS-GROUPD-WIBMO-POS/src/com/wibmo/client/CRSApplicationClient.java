/**
 * 
 */
package com.wibmo.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.wibmo.business.*;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.NoCourseAvailableException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.StudentNotFoundException;
import com.wibmo.bean.User;

/**
 * 
 */
public class CRSApplicationClient {
	
	static final StudentOperation studentOp = new StudentOperationImpl();

	/**
	 * @param args
	 * @throws StudentNotFoundException 
	 * @throws NoCourseAvailableException 
	 * @throws DuplicateCourseEntryException 
	 * @throws StudentAlreadyRegisteredException 
	 */
	public static void main(String[] args) throws DuplicateCourseEntryException, NoCourseAvailableException, StudentNotFoundException, StudentAlreadyRegisteredException {
		// TODO Auto-generated method stub
		LocalDate localDate = LocalDate.now();

        LocalTime localTime = LocalTime.now();

        Scanner scan=new Scanner(System.in);

        System.out.println("===================================================================================");

        System.out.format("\t%15s%s","\t","Welcome to the CRS application\n");

        System.out.format("\t%15s\t%s %s %s","\t",localDate.getDayOfMonth(),localDate.getMonth(),localDate.getYear());

        System.out.println();

        System.out.format("\t%15s     %s","\t",localTime);

        

        System.out.println();

        System.out.println("===================================================================================");
        
        while(true) {
        	System.out.println("1. Login"
        			+"\n2. Registration"
        			+"\n3. Update Password"
        			+"\n4. Exit");
        	System.out.print("\nEnter your choice: ");
        	int choice = scan.nextInt();
        	boolean flag = false;
        	switch(choice) {
        	case 1:
        		System.out.print("Enter your Role "
        				+"\n1. Student"
        				+"\n2. Professor"
        				+"\n3. Admin");
        		System.out.print("\nEnter your role Id: ");
        		int role = scan.nextInt();
        		System.out.print("\nEnter your UserId: ");
        		int username = scan.nextInt();
        		System.out.print("\nEnter your Password: ");
        		String password = scan.next();
        		
        		//Authentication
        		AuthenticationOperation loggedin=new AuthenticationOperationImpl();
        		
        		if(loggedin.loggedin(username, password)) {
        			
        		switch(role) {
        		case 1:
        			System.out.println("\nYou are logged in successfully as a student");
        			CRSStudentMenu studentMenu = new CRSStudentMenu();
        			studentMenu.studentMenu();
        			break;
        		case 2: //Professor
        			CRSProfessorMenu professorMenu = new CRSProfessorMenu();
        			professorMenu.professorMenu();
        			break;
        		case 3: 
        			CRSAdminMenu adminMenu = new CRSAdminMenu();
        			adminMenu.adminMenu();
        			break;
        		}}
        		else {
        			System.out.println("invalid credentials");
        		}
        			
        		break;
        	case 2:
        		CRSStudentMenu studentMenu = new CRSStudentMenu();
    			studentMenu.studentRegistration();
        		break;
        	case 3:
        		System.out.print("Enter Username:");
        		username = scan.nextInt();
        		System.out.print("\nEnter Current Password: ");
        		password = scan.next();
        		System.out.println("\nChange Password");
        		while(true) {
        			System.out.print("\nEnter New Password: ");
            		String passwordOne = scan.next();
            		System.out.print("\nEnter Password Again: ");
            		String passwordAgain = scan.next();
            		if(passwordOne.equals(passwordAgain))
            			break;
            		else
            			System.out.println("Password does not match");
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
