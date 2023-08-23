/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case a course is already registered.
 */
@Component
public class DuplicateCourseEntryException extends Exception {
	private int courseId;
	
	public DuplicateCourseEntryException() {
		
	}
	
	public DuplicateCourseEntryException(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCourseid() {
		return courseId;
	}
}
