/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
