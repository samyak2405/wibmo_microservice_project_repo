/**
 * 
 */
package com.wibmo.business;

import java.util.List;

import com.wibmo.bean.Admin;
import com.wibmo.bean.User;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;

/**
 * 
 */
public interface AdminOperation {
	//Approve Student Registration
	/**
	 * 
	 */	
	public void approveStudent();
 
	//Approve Student Course Registration
	/**
	 * 
	 */
	public void approveCourseRegistration();
 
	/**
	 * 
	 */
	public void addAdmin(Admin admin);
 
	/**
	 * 
	 */
	public void assignCoursesProf();
 
	/**
	 * 
	 */
	public void adminRegistration(Admin user) throws UserAlreadyExistsException;

	/**
	 * 
	 */
	public void approveStudentById() throws UserNotFoundException;
}
