/**
 * 
 */
package com.wibmo.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.wibmo.exception.StudentAlreadyRegisteredException;


/**
 * Student Operation Interface
 */
@Service
@Transactional
public interface StudentOperation {

		/**
	 * To check if a student is registered of not.
	 * @param userId
	 * @return True if student is registered else returns False.
	 */
	public int isApproved(int userId) ;

	/**
	 * To get the Student by Email
	 * @param userEmail
	 * @return count of student with given email
	 */
	public int getStudentByEmail(String userEmail);

	/**
	 * Check whether student is registered or not
	 * 
	 * @param userId
	 * @return 1 if student is registered
	 * @throws StudentAlreadyRegisteredException 
	 */
	public Integer isStudentRegistered(int userId) throws StudentAlreadyRegisteredException;

	boolean innerAuthenticate(Integer userId, String jwt);
	
//	/**
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	public boolean isRegistered(int userId) ;
	
}
		