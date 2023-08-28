/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mapping class for professor and Courses
 */
@Entity
@Table(name="professorcoursemapping")
public class ProfessorCourseMap implements Serializable {
	@Id
	@Column
	private int studentId;
	@Id
	@Column
	private int courseId;
	
	@ManyToOne
	@JoinColumn(name="courseId")
	private CourseCatalog course;
	
	@Column
	private int isApproved;
	
	
	
    /**
	 * @return the course
	 */
	public CourseCatalog getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(CourseCatalog course) {
		this.course = course;
	}

	/**
	 * @return the isApproved
	 */
	public int getIsApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
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
}
