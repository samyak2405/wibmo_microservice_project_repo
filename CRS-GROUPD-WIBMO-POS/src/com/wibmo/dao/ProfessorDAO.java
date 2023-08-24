/**
 * 
 */
package com.wibmo.dao;

import java.util.List;
import java.util.Map;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.Professor;
import com.wibmo.bean.Student;

/**
 * To perform read and write operations in the database for Professor operations.
 */
public interface ProfessorDAO {
	
	/**
	 * To set the student grades in the database using SQL Queries.
	 * @param studentId
	 * @param courseId
	 * @param grade
	 */
	
	public void setGrades(long studentId,Integer courseId,String grade) ;
	
	/**
	 * To request to teach a specific course usign SQL Queries.
	 * @param professorId
	 * @param courseIdList
	 * @return
	 */
	public boolean requestCourseOffering(int professorId,List<Integer> courseIdList);
	
	/**
	 * To get the list of registered students using SQL queries.
	 * @param courseId
	 * @return List of registered student.
	 */
	public List<Student> viewStudentList(Integer courseId);
	
	/**
	 * 	To view list of all available courses in the database using SQL queries.
	 * @return list of courses.
	 */
	public  List<CourseCatalog> viewCourseCatalog();
	
	/**
	 * To register a new professor in the database using SQL queries.
	 * @param professor
	 */
	public void registerProfessor(Professor professor);
	/**
	 * To search a new professor in the database using SQL queries.
	 * @param userId
	 * @return True if professor of given userId present else returns false.
	 */
	public boolean searchProfessor(String userEmail);
	
	/**
	 * 
	 * @param userEmail
	 * @return id of professor
	 */
	public int getProfessorById(String userEmail);
	
	/**
	 * To get list of courses approved by admin.
	 * @param userId
	 * @return list of approved courses
	 */

	public Map<Integer, String> listOfApprovedCourses(int userId);

	
}
