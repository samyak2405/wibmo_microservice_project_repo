/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case user is already present/registered/approved.
 */

@Component
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
