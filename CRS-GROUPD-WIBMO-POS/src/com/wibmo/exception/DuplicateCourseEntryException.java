/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
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
