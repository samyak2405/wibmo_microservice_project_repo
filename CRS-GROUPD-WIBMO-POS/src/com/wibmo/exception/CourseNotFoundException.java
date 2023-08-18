/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class CourseNotFoundException extends Exception{
long userId;
	
	
	public CourseNotFoundException () {
		
	}
	
	public CourseNotFoundException (long userId) {
		this.userId = userId;
	}
	
	public long getCourseId()  {
		return userId;
	}
}
