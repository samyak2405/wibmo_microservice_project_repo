/**
 * 
 */
package com.wibmo.bean;

import java.util.Map;

/**
 * 
 */
public class StudentCourseMap {

	private int studentId;
	private Map<Integer,Integer> courses;
	private int isRegister;
	
	public int getIsRegister() {
		return isRegister;
	}
	/**
	 * @return the courses
	 */
	public Map<Integer, Integer> getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Map<Integer, Integer> courses) {
		this.courses = courses;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
}
