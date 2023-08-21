/**
 * 
 */
package com.wibmo.dao;

/**
 * To perform read and write operations in database for different courses.
 */
public interface CourseDAO {

	/**
	 * To search for specific courses
	 * @param courseId
	 * @return True if course is found else returns False.
	 */
	public boolean searchCourse(long courseId);
}
