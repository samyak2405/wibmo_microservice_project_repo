/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case student is already registered
 */

@Component
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
