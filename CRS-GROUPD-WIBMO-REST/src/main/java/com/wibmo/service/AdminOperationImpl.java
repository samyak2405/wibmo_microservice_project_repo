package com.wibmo.service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import com.wibmo.model.*;
import com.wibmo.repository.*;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.*;


@Service
public class AdminOperationImpl implements AdminOperation{
	
	private Logger log=LogManager.getLogger();
	
	Scanner scan = new Scanner(System.in);
	

	@Autowired
	AdminDAO adminDAO;
	@Autowired
	StudentDAOImpl studentDAO;
	
	@Autowired
	public ValidatorInterface validate;
	@Autowired
	public NotificationOperation notification;
	

	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		adminDAO.setApprovedStudents();
	}

	@Override
	public void addAdmin(User user) {
		// TODO Auto-generated method stub
		adminDAO.addAdmin(user);
	}

	@Override
	public void assignCoursesProf() {
		validate.assignCourseValidator();

	}

	@Override
	public void adminRegistration(User user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		if(validate.emailValidator(user.getUserEmail()))
			{
			if(adminDAO.searchAdmin(user.getUserId()))
			{
				throw new UserAlreadyExistsException(user.getUserEmail());
			}
			this.addAdmin(user);
			
			}
		else
			//System.out.println("Invalid Email Id");
			log.info("Invalid Email Id");
	}

	@Override
	public List<Boolean> approveCourseRegistration() {
		// TODO Auto-generated method stub
		List<Boolean> isSuccess = validate.courseRegistrationValidator();
		return isSuccess;
	}

	@Override
	public void approveStudentById(int id) throws UserNotFoundException{
		// TODO Auto-generated method stub
//		List<Integer> studentIds = adminDAO.pendingRegistration();
//
//		System.out.println();
//
//        System.out.println("===================================================================================");
//		log.info("Choose from below given student ids");
//		studentIds.forEach(studentId->log.info(String.format("%20s\n", studentId)));
//		System.out.println();
//
//        System.out.println("===================================================================================");
//		log.info("Enter the StudentId: ");
//
//		
//		
//
//		int studentId = scan.nextInt();
		if(studentDAO.searchStudentByID(id)==false) {
			throw new UserNotFoundException(id);
		}
		adminDAO.setApprovedStudentById(id);
	}

	@Override
	public int getAdminById(String userEmail) {
		// TODO Auto-generated method stub
		return adminDAO.getAdminById(userEmail);
	}

	

}
