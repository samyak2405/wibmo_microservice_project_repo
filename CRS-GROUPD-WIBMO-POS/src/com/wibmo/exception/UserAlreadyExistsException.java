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
	
	public UserAlreadyExistsException  (int userId) {
		this.userId = userId;
	}
	
	public int getStudentId()  {
		return userId;
	}
}
