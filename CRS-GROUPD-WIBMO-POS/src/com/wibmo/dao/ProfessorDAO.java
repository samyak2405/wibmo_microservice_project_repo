/**
 * 
 */
package com.wibmo.dao;

import java.util.List;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.Professor;
import com.wibmo.bean.Student;

/**
 * 
 */
public interface ProfessorDAO {
	
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 * @param grade
	 */
	
	public void setGrades(long studentId,long courseId,String grade);
	public boolean requestCourseOffering(int professorId,List<Long> courseIdList);
	public List<Student> viewStudentList(long courseId);
	public  List<CourseCatalog> viewCourseCatalog();
	public void registerProfessor(Professor professor);
	
}
