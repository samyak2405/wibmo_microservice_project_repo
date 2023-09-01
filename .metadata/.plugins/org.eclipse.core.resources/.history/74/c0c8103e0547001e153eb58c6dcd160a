/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case user does not exist.
 */
@Component
public class UserNotFoundException extends Exception {

	long userId;
	
	
	public UserNotFoundException () {
		
	}
	
	public UserNotFoundException (long userId) {
		this.userId = userId;
	}
	
	public long getUserId()  {
		return userId;
	}
	
}
