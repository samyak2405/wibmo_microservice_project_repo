/**
 * 
 */
package com.wibmo.dto;

/**
 * Data Transfer Object for Grade Card
 */
public class GradeCardDto {

	private int studentId;
	private int courseId;
	private String grades;

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
	 * @return the grades
	 */
	public String getGrades() {
		return grades;
	}

	/**
	 * @param grades the grades to set
	 */
	public void setGrades(String grades) {
		this.grades = grades;
	}

}
