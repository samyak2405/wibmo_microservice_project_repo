package com.wibmo.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wibmo.bean.*;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.dao.*;
import com.wibmo.validator.*;

public class AdminOperationImpl implements AdminOperation{

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
	public void adminRegistration(Admin user) {
		// TODO Auto-generated method stub
		if(validate.emailValidator(user.getUserEmail()))
			adminOp.addAdmin(user);
		else
			System.out.println("Invalid Email Id");
	}

	@Override
	public void approveCourseRegistration() {
		// TODO Auto-generated method stub
		validate.courseRegistrationValidator();
	}

	@Override
	public void approveStudentById() {
		// TODO Auto-generated method stub
		List<Integer> studentIds = adminDAO.pendingRegistration();
		System.out.println("Choose from below given student ids");
		studentIds.forEach(studentId->System.out.println(String.format("%20s\n", studentId)));
		System.out.println("Enter the StudentId: ");
		int studentId = scan.nextInt();
		adminDAO.setApprovedStudentById(studentId);
	}

}
