/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class CourseNotAssignedException extends Exception {
	
	String courseId;
	public CourseNotAssignedException() {
		
	}
	
	public CourseNotAssignedException(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseId() {
		return courseId;
	}
}
