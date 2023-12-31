/**
 * 
 */
package com.wibmo.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case number of courses exceed the permissible number of courses
 */

@Component
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
