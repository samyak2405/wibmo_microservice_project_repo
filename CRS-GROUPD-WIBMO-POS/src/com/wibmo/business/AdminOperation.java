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
 * For performing various Admin operations
 */
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
	public void addAdmin(Admin admin);
 
	/**
	 * To assign which course will be taught by which professor
	 */
	public void assignCoursesProf();
 
	/**
	 * To approve registration of a new Admin
	 */
	public void adminRegistration(Admin user) throws UserAlreadyExistsException;

	/**
	 * To approve a specific student
	 */
	public void approveStudentById() throws UserNotFoundException;


	public int getAdminById(String userEmail);

}
