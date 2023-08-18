/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class StudentAlreadyRegisteredException extends Exception 
{

	long userId;
	
	
	public StudentAlreadyRegisteredException() {
		
	}
	
	public StudentAlreadyRegisteredException(long userId) {
		this.userId = userId;
	}
	
	public long getStudentId() {
		return userId;
	}
	

}
