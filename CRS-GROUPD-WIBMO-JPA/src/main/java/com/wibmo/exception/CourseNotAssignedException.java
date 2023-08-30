/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception to handle courseAssignment
 */
public class CourseNotAssignedException extends Exception {

	long courseId;

	public CourseNotAssignedException() {

	}

	/**
	 * sets the courseId
	 * 
	 * @param courseId
	 */
	public CourseNotAssignedException(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * returns the courseId
	 * 
	 * @return courseId
	 */
	public long getCourseId() {
		return courseId;
	}
}
