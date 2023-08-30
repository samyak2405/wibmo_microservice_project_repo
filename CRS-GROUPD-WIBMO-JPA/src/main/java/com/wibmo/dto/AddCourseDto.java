/**
 * 
 */
package com.wibmo.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class AddCourseDto {
	
	
	Map<String,Integer> courses= new HashMap<>();

	public Map<String, Integer> getCourses() {
		return courses;
	}

	public void setCourses(Map<String, Integer> courses) {
		this.courses = courses;
	}

}
