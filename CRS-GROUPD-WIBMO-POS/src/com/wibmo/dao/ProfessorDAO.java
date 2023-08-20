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
	
	public void setGrades(long studentId,Integer courseId,String grade) ;
	public boolean requestCourseOffering(int professorId,List<Integer> courseIdList);
	public List<Student> viewStudentList(Integer courseId);
	public  List<CourseCatalog> viewCourseCatalog();
	public void registerProfessor(Professor professor);
	public boolean searchProfessor(String userEmail);
	public int getProfessorById(String userEmail);

	
}
