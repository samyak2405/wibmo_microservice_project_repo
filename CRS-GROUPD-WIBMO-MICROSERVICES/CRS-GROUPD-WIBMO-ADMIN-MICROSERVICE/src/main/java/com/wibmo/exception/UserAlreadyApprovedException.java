/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class UserAlreadyApprovedException extends Exception {
	int userId;
	
	public UserAlreadyApprovedException() {

	}

	/**
	 * sets the userId
	 * 
	 * @param userId
	 */
	public UserAlreadyApprovedException(int userId) {
		this.userId = userId;
	}

	/**
	 * returns the userId
	 * 
	 * @return userId
	 */

	public int getUserId() {
		return userId;
	}

}
