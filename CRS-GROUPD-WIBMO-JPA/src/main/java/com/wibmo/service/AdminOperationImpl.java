package com.wibmo.service;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import com.wibmo.entity.*;
import com.wibmo.repository.*;
import com.wibmo.entity.User;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.*;


@Service
public class AdminOperationImpl implements AdminOperation{
	
	private Logger log=LogManager.getLogger();
	
	Scanner scan = new Scanner(System.in);
	
	@Autowired
	public AdminRepository adminRepository;

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
		adminRepository.save(user);
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
			if(adminDAO.searchAdmin(user.getUserId())>0)
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
