/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class StudentAlreadyRegisteredException extends Exception 
{

	int userId;
	
	
	public StudentAlreadyRegisteredException() {
		
	}
	
	public StudentAlreadyRegisteredException(int userId) {
		this.userId = userId;
	}
	
	public int getStudentId() {
		return userId;
	}
	

}
