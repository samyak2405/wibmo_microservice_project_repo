package com.wibmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.jwt.JwtTokenUtils;
import com.wibmo.repository.*;


/**
 * Student Operation Implementation
 */
@Service
public class StudentOperationImpl implements StudentOperation {

	@Autowired
	public StudentRepository studentDao;
	@Autowired
	public JwtTokenUtils jwtTokenUtils;

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
	 * @throws StudentAlreadyRegisteredException 
	 */
	@Override
	public Integer isStudentRegistered(int userId) throws StudentAlreadyRegisteredException {
		// TODO Auto-generated method stub
		Integer isRegistered = studentDao.countByUserIdAndCourseRegistrationStatus(userId, 1);
		if(isRegistered==1)
			throw new StudentAlreadyRegisteredException(studentDao.findById(userId).get().getUserEmail());
		return isRegistered;
	}
	
	@Override
	public boolean innerAuthenticate(Integer userId, String jwt) {
		
		   String token = jwt.substring(jwt.lastIndexOf("Bearer ")+7);
		   if(jwtTokenUtils.getAllClaimsFromToken(token).get("userId").toString().equals(userId.toString()))
		   return true;
		return false;
	}
}
