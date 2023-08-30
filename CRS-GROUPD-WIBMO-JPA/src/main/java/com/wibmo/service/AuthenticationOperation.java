/**
 * 
 */
package com.wibmo.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

/**
 * Authenticating Users Interface
 */
@Service
@Transactional
public interface AuthenticationOperation {

	/*
	 * Check whether User with details can log into system or not
	 * 
	 * @param User Email
	 * 
	 * @param User Password
	 * 
	 * @param User Role
	 * 
	 * @return true if user details matched otherwise return false
	 */
	public boolean loggedin(String userEmail, String userPassword, int role, StringBuilder msg);

	/*
	 * Update User Password
	 * 
	 * @param User Email
	 * 
	 * @param User Password
	 * 
	 * @param User Role
	 */
	public void updatePassword(String userEmail, String userPassword, int role);

}
