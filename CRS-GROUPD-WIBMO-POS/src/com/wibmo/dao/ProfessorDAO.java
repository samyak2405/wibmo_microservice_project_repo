/**
 * 
 */
package com.wibmo.dao;

import java.util.List;

import com.wibmo.bean.CourseCatalog;

/**
 * 
 */
public interface ProfessorDAO {
	public void setGrades(long studentId,long courseId);
	  public boolean requestCourseOffering(List<Long> courseIdList);
	  public void viewStudentList(long courseId);
	  public  List<CourseCatalog> viewCourseCatalog();
}
