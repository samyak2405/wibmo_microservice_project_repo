/**
 * 
 */
package com.wibmo.dto;

import java.io.Serializable;

/**
 * 
 */
public class DropCourseDTO implements Serializable {
	private int studentId;
	private int courseId;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	
}
