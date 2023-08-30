/**
 * 
 */
package com.wibmo.dto;

import java.io.Serializable;

/**
 * Data transfer Object for Course Deletion
 */
public class DropCourseDTO implements Serializable {
	private int studentId;
	private int courseId;

	/**
	 * @return returns studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param sets studentId
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return returns courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param sets courseId
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}
