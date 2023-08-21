/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case student is already registered
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
