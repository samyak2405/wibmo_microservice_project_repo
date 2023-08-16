/**
 * 
 */
package com.wibmo.dao;

import java.util.List;
import java.util.Set;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.GradeCard;
import com.wibmo.bean.Student;
import com.wibmo.bean.StudentCourseMap;

/**
 * 
 */
public interface StudentDAO {
//	public void registerCourses();
	
	/**
	 * To add courses for registration using SQL queries
	 * @param studCoMap
	 */
	public void addCourses(StudentCourseMap studCoMap);
	
	/**
	 * To drop courses for registration using SQL queries
	 * @param studentId
	 * @param courseId
	 * @param courses
	 * @return
	 */
	public int dropCourses(long studentId, int courseId,Set<Integer> courses);
	
	
	/**
	 * To return list of registered courses using SQL queries
	 * @return
	 */
	public List<String> listCourse();
	
	
	/**
	 * To add return list of offered courses using SQL queries
	 * @return
	 */
	public List<CourseCatalog> viewCourseCatalog();
	
	/**
	 * To register a new student in the database using SQL queries
	 * @param student
	 */
	public void registerStudent(Student student);
	
	/**
	 * To fetch student grade card using SQL queries
	 * @param studentId
	 * @return
	 */
	public List<GradeCard> viewReportCard(int studentId);
}
