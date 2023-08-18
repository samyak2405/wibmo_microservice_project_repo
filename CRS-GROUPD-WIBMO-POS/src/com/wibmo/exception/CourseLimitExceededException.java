/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class CourseLimitExceededException {
int userId;
	
	
	public CourseLimitExceededException  () {
		
	}
	
	public CourseLimitExceededException  (int userId) {
		this.userId = userId;
	}
	
	public int getStudentId()  {
		return userId;
	}
}
