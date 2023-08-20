/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class StudentAlreadyRegisteredException extends Exception 
{

	String userEmail;
	
	
	public StudentAlreadyRegisteredException() {
		
	}
	
	public StudentAlreadyRegisteredException(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getStudentEmail() {
		return userEmail;
	}
	

}
