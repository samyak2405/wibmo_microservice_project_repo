/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case a course is already registered.
 */
public class DuplicateCourseEntryException extends Exception {
	private int courseId;
	
	public DuplicateCourseEntryException() {
		
	}
	
	
	/**
	 * DuplicateCourseEntryException
	 * @param courseId
	 */
	
	public DuplicateCourseEntryException(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCourseid() {
		return courseId;
	}
}
