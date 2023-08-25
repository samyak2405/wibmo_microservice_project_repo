/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case User has not been registered/approved
 */
@Component
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
