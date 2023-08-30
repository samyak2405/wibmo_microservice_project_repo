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
	private String courseId;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
}
