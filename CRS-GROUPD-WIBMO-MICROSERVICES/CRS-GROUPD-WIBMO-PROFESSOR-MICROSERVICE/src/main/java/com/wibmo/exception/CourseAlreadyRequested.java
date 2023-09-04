/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class CourseAlreadyRequested extends Exception {
	int userId;

	public CourseAlreadyRequested() {

	}

	/**
	 * sets the userId
	 * 
	 * @param userId
	 */
	public CourseAlreadyRequested(int userId) {
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
