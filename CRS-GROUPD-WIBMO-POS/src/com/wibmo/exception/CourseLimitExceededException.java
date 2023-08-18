/**
 * 
 */
package com.wibmo.exception;

/**
 * 
 */
public class CourseLimitExceededException extends Exception {

	long courseId;
	public CourseLimitExceededException  () {
		
	}
	
	public CourseLimitExceededException  (long courseId) {
		this.courseId = courseId;
	}
	
	public long getCourseId()  {
		return courseId;
	}
}
