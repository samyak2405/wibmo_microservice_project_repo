/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case user is already present/registered/approved.
 */
public class UserAlreadyExistsException extends Exception {
long userId;
	
	
	public UserAlreadyExistsException () {
		
	}
	
	public UserAlreadyExistsException(long userId) {
		this.userId = userId;
	}
	
	public long getUserId()  {
		return userId;
	}
}
