/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case User has not been registered/approved
 */
public class UserNotApprovedException extends Exception {
int userId;
	
	
	public UserNotApprovedException () {
		
	}
	
	/**
	 * UserNotApprovedException
	 * @param userId
	 */
	public UserNotApprovedException (int userId) {
		this.userId = userId;
	}
	
	public int getUserId()  {
		return userId;
	}
}
