/**
 * 
 */
package com.wibmo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wibmo.model.Admin;
import com.wibmo.model.User;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;

/**
 * For performing various Admin operations
 */


@Service
public interface AdminOperation {
	
	
	
	/**
	 * To Approve Student Registration
	 */	
	public void approveStudent();
 
	
	/**
	 * To Approve Student Course Registration
	 */
	public void approveCourseRegistration();
 
	/**
	 * To Add a new Admin
	 */
	public void addAdmin(User admin);
 
	/**
	 * To assign which course will be taught by which professor
	 */
	public void assignCoursesProf();
 
	/**
	 * To approve registration of a new Admin
	 */
	public void adminRegistration(User user) throws UserAlreadyExistsException;

	/**
	 * To approve a specific student
	 */
	public void approveStudentById(int id) throws UserNotFoundException;


	public int getAdminById(String userEmail);

}
