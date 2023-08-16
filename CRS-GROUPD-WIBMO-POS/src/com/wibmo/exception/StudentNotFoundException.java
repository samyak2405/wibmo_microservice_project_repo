/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class StudentNotFoundException extends Exception {

	int userId;
	
	
	public StudentNotFoundException () {
		
	}
	
	public StudentNotFoundException (int userId) {
		this.userId = userId;
	}
	
	public int getStudentId()  {
		return userId;
	}
	
}
