/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class UserAlreadyExistsException extends Exception {
	
	private String userEmail;
	
	
	public UserAlreadyExistsException () {
		
	}
	
	public UserAlreadyExistsException(String userEmail) {
		this.userEmail= userEmail;
	}
	
	public String getUserId()  {
		return userEmail;
	}
}
