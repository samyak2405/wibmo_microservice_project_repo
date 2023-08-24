/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case user does not exist.
 */
public class UserNotFoundException extends Exception {

	long userId;
	
	
	public UserNotFoundException () {
		
	}
	
	
	/**
	 * UserNotFoundException
	 * @param userId
	 */
	public UserNotFoundException (long userId) {
		this.userId = userId;
	}
	
	public long getUserId()  {
		return userId;
	}
	
}
