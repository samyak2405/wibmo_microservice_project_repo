package com.wibmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wibmo.repository.*;


/**
 * Student Operation Implementation
 */
@Service
public class StudentOperationImpl implements StudentOperation {

	@Autowired
	public StudentRepository studentDao;

	/**
	 * To check if a student is registered of not.
	 * 
	 * @param userId
	 * @return True if student is registered else returns False.
	 */
	@Override
	public int isApproved(int userId) {
		// TODO Auto-generated method stub
		int flag = studentDao.isApproved(userId);
		return flag;
	}


	/**
	 * To get the Student by Email
	 * 
	 * @param userEmail
	 * @return count of student with given email
	 */
	@Override
	public int getStudentByEmail(String userEmail) {
		int studentId = studentDao.findByEmail(userEmail);
		return studentId;
	}


	/**
	 * Check whether student is registered or not
	 * 
	 * @param userId
	 * @return 1 if student is registered
	 */
	@Override
	public Integer isStudentRegistered(int userId) {
		// TODO Auto-generated method stub
		Integer isRegistered = studentDao.isStudentRegistered(userId);
		return isRegistered;
	}

}
