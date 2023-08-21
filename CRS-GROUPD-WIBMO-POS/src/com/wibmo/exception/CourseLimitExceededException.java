/**
 * 
 */
package com.wibmo.exception;

/**
 * Exception in case number of courses exceed the permissible number of courses
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
