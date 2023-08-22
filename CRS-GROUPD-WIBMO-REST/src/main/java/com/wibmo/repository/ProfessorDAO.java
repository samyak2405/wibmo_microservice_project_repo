package com.wibmo.repository;

import java.util.List;



import java.util.Map;

import com.wibmo.model.CourseCatalog;
import com.wibmo.model.Professor;
import com.wibmo.model.Student;
import org.springframework.stereotype.Repository;

@Repository
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
	public int getProfessorById(String userEmail);

	public Map<Integer, String> listOfApprovedCourses(int userId);

	
}

