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
	 * @param admin
	 */
	public void addAdmin(User admin);
 
	/**
	 * To assign which course will be taught by which professor
	 */
	public void assignCoursesProf();
 
	/**
	 * To approve registration of a new Admin
	 * @param user
	 * @throws UserAlreadyExistsException
	 */
	public void adminRegistration(User user) throws UserAlreadyExistsException;

	/**
	 * To approve a specific student
	 * @throws UserNotFoundException
	 */
	public void approveStudentById() throws UserNotFoundException;

    /**
     * To get id of admin
     * @param userEmail
     * @return
     */
	public int getAdminById(String userEmail);

}
