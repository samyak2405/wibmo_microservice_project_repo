/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class UserNotApprovedException extends Exception {
int userId;
	
	
	public UserNotApprovedException () {
		
	}
	
	public UserNotApprovedException (int userId) {
		this.userId = userId;
	}
	
	public int getUserId()  {
		return userId;
	}
}
