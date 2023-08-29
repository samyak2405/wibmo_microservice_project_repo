/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class representing Grade Card
 */
@Entity
@Table(name="gradecard")
public class GradeCard implements Serializable {
	@Id
	@Column
	private int studentId;
	
	@Id
	@Column
//	@ManyToOne
//	@JoinColumn(name="courseId")
	private int courseId;
	
	@Column
	private String grade;

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
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
