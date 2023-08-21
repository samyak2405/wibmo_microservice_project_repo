/**
 * 
 */
package com.wibmo.business;

/**
 * To authenticate different use cases
 */
public interface AuthenticationOperation {
	
	/*
	 * Check whether User with details can log into system or not
	 * @param User Email
	 * @param User Password
	 * @param User Role
	 * @return true if user details matched otherwise return false
	 * */
	public boolean loggedin(String userEmail ,String password ,int role);
	
	/*
	 * Update User Password
	 * @param User Email
	 * @param User Password
	 * @param User Role
	 */
	public void updatePassword(String userEmail,String password,int role);

}
