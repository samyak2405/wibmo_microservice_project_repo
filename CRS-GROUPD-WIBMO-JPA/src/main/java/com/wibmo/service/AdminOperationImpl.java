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
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.*;


@Service
public class AdminOperationImpl implements AdminOperation{
	
	private Logger log=LogManager.getLogger();
	
	Scanner scan = new Scanner(System.in);
	

	@Autowired
	AdminRepository adminDAO;
	@Autowired
	StudentRepository studentDAO;
	
	@Autowired
	public ValidatorInterface validate;
	@Autowired
	public NotificationOperation notification;
	

	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		adminDAO.approveStudentRegistration();
	}

	@Override
	public void approveStudentById(int userId) throws UserNotFoundException{
		// TODO Auto-generated method stub

		if(studentDAO.findById(userId)==null) {
			throw new UserNotFoundException(userId);
		}
		adminDAO.approveStudentRegistrationById(userId);
	}
	@Override
	public void addAdmin(Admin user) {
		// TODO Auto-generated method stub
		adminDAO.save(user);
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
			if(adminDAO.findById(user.getUserId())!=null)
			{
				throw new UserAlreadyExistsException(user.getUserEmail());
			}
			Admin admin = new Admin();
			
			admin.setUserName(user.getUserName());
			admin.setUserEmail(user.getUserEmail());
			admin.setUserPhonenumber(user.getUserPhonenumber());
			admin.setUserPassword(user.getUserPassword());
			
			this.addAdmin(admin);
			
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
	public int getAdminById(String userEmail) {
		// TODO Auto-generated method stub
		return adminDAO.getAdminById(userEmail);
	}

	

	

}
