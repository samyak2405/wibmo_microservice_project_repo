package com.wibmo.repository;

import org.springframework.stereotype.Repository;

@Repository 
public interface CourseDAO {
	/**
	 * To search for specific courses
	 * @param courseId
	 * @return True if course is found else returns False.
	 */
	public boolean searchCourse(int courseId);
}
