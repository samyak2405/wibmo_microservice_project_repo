/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case specific course is not found
 */
@Component
public class CourseNotFoundException extends Exception {

	long courseId;

	public CourseNotFoundException() {

	}

	/**
	 * Sets the courseId
	 * 
	 * @param courseId
	 */
	public CourseNotFoundException(long courseId) {
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
