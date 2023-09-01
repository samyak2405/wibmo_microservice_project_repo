/**
 * 
 */
package com.wibmo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wibmo.dto.RegisterUserDto;
import com.wibmo.entity.Admin;
import com.wibmo.entity.User;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;

/**
 * Admin Operations Interface
 */

@Service
public interface AdminOperation {

	/**
	 * To Approve Student Registration
	 */
	public void approveStudent();

	/**
	 * To Add a new Admin
	 * 
	 * @param User contains admin details
	 */
	public void approveAdmin(int userId);

	/**
	 * To assign which course will be taught by which professor
	 */
	public void assignCoursesProf();

	/**
	 * To approve registration of a new Admin
	 * 
	 * @param User contains admin details
	 */
	public void adminRegistration(RegisterUserDto user) throws UserAlreadyExistsException;

	/**
	 * To Approve Student Course Registration
	 * 
	 * @return List<Boolean> contains true if courses are assigned successfully to
	 *         student otherwise false
	 */
	public Map<Integer,Boolean> approveCourseRegistration();

	/**
	 * To approve a specific student
	 * 
	 * @param studentId
	 */
	public void approveStudentById(int id) throws UserNotFoundException;

	/**
	 * To get admin by Email
	 * 
	 * @param userEmail
	 * @return
	 */
	public int getAdminByEmail(String userEmail);

}
