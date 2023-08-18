/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
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
