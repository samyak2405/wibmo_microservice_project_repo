/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case specific course is not found
 */
public class CourseNotFoundException extends Exception{
	
	
	

	long courseId;
	
	
	public CourseNotFoundException() {
		
	}
	
	public CourseNotFoundException(long courseId) {
		this.courseId = courseId;
	}
	
	public long getCourseId() {
		return courseId;
	}
	

}
