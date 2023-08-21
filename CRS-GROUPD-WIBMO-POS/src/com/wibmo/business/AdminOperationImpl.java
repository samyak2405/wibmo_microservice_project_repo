package com.wibmo.business;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wibmo.bean.*;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.dao.*;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.*;

public class AdminOperationImpl implements AdminOperation{
	
	static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	
	Scanner scan = new Scanner(System.in);
	static AdminOperationImpl adminOp = new AdminOperationImpl();
	AdminDAO adminDAO = AdminDAOImpl.getInstance();
	StudentDAOImpl studentDAO = StudentDAOImpl.getInstance();
	public ValidatorInterface validate = new AdminValidatorImpl();
	public NotificationOperation notification=new NotificationOperationImpl();
	

	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		adminDAO.setApprovedStudents();
	}

	@Override
	public void addAdmin(Admin user) {
		// TODO Auto-generated method stub
		adminDAO.addAdmin(user);
	}

	@Override
	public void assignCoursesProf() {
		validate.assignCourseValidator();

	}

	@Override
	public void adminRegistration(Admin user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		if(validate.emailValidator(user.getUserEmail()))
			{
			if(adminDAO.searchAdmin(user.getUserId()))
			{
				throw new UserAlreadyExistsException(user.getUserId());
			}
			adminOp.addAdmin(user);
			
			}
		else
			//System.out.println("Invalid Email Id");
			log.info("Invalid Email Id");
	}

	@Override
	public void approveCourseRegistration() {
		// TODO Auto-generated method stub
		validate.courseRegistrationValidator();
	}

	@Override
	public void approveStudentById() throws UserNotFoundException{
		// TODO Auto-generated method stub
		List<Integer> studentIds = adminDAO.pendingRegistration();

		System.out.println();

        System.out.println("===================================================================================");
		log.info("Choose from below given student ids");
		studentIds.forEach(studentId->log.info(String.format("%20s\n", studentId)));
		System.out.println();

        System.out.println("===================================================================================");
		log.info("Enter the StudentId: ");

		
		

		int studentId = scan.nextInt();
		if(studentDAO.searchStudent(studentId)==false) {
			throw new UserNotFoundException(studentId);
		}
		adminDAO.setApprovedStudentById(studentId);
	}

}
