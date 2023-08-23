/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case specific course is not found
 */
@Component
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
