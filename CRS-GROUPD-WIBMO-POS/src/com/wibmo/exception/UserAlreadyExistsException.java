/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case user is already present/registered/approved.
 */
public class UserAlreadyExistsException extends Exception {
	
	private String userEmail;
	
	
	public UserAlreadyExistsException () {
		
	}
	
	/**
	 * UserAlreadyExistsException
	 * @param userEmail
	 */
	public UserAlreadyExistsException(String userEmail) {
		this.userEmail= userEmail;
	}
	
	public String getUserId()  {
		return userEmail;
	}
}
