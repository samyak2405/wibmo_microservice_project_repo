/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class UserAlreadyExistsException extends Exception {
int userId;
	
	
	public UserAlreadyExistsException () {
		
	}
	
	public UserAlreadyExistsException(long userId) {
		this.userId = userId;
	}
	
	public long getUserId()  {
		return userId;
	}
}
