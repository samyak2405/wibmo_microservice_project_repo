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
	
	@Id
	@Column(name="courseid")
	private int courseId;
	
	@Column(name="coursecategory")
	private int coursePref;
	
	@Column(name="isRegister")
	private int isRegister;
	
	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the coursePref
	 */
	public int getCoursePref() {
		return coursePref;
	}

	/**
	 * @param coursePref the coursePref to set
	 */
	public void setCoursePref(int coursePref) {
		this.coursePref = coursePref;
	}

	/**
	 * @param isRegister the isRegister to set
	 */
	public void setIsRegister(int isRegister) {
		this.isRegister = isRegister;
	}

	public int getIsRegister() {
		return isRegister;
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
