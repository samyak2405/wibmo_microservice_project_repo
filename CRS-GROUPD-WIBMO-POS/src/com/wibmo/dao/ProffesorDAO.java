/**
 * 
 */
package com.wibmo.dao;

import java.util.List;

/**
 * 
 */
public interface ProffesorDAO {
	public void setGrades(long studentId,long courseId);
	  public boolean requestCourseOffering(List<Long> courseIdList);
	  public void viewStudentList(long courseId);
	  public  void viewCourseCatalog();
}
