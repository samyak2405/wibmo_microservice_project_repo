/**
 * 
 */
package com.wibmo.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Data transfer Object for Course addition
 */
public class AddCourseDto {
	
	Map<Integer, Integer> courses = new HashMap<>();

	/**
	 * @return returns courses
	 */
	public Map<Integer, Integer> getCourses() {
		return courses;
	}

	/**
	 * @param map of courses
	 */
	public void setCourses(Map<Integer, Integer> courses) {
		this.courses = courses;
	}

}
