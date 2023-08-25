/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Class representing mapping between Students and Courses.
 */
@Entity
@Table(name="studentcoursemapping")
public class StudentCourseMap implements Serializable
{
	@Id
	@Column(name="studentid")
	private int studentId;
	
	private Map<Integer,Integer> courses;
	
	@Column
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
