/**
 * 
 */
package com.wibmo.model;

/**
 * Mapping class for professor and Courses
 */
public class ProfessorCourseMap {
	private int studentId;
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
