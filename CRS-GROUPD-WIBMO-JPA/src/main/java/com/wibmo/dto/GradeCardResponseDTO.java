/**
 * 
 */
package com.wibmo.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 
 */
public class GradeCardResponseDTO {
	private int studentId;
	Map<String,Map<String,String>>gradeDetails=new HashMap<>();
	
	public void addGradeDetails(String courseId,String courseName,String grades)
	{
		Map<String,String>innerMap=new HashMap<>();
		innerMap.put(courseName, grades);
		gradeDetails.put(courseId, innerMap);
	}
	
	/**
	 * @return the gradeDetails
	 */
	public Map<String, Map<String, String>> getGradeDetails() {
		return gradeDetails;
	}
	/**
	 * @param gradeDetails the gradeDetails to set
	 */
	public void setGradeDetails(Map<String, Map<String, String>> gradeDetails) {
		this.gradeDetails = gradeDetails;
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