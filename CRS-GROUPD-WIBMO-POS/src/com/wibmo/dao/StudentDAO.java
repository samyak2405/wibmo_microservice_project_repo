/**
 * 
 */
package com.wibmo.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.GradeCard;
import com.wibmo.bean.Student;
import com.wibmo.bean.StudentCourseMap;

/**
 * 
 */
public interface StudentDAO {
	public void registerCourses(int studentId);
	
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
	public Map<Integer,String> listCourse(int studentid);
	
	
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

	/**
	 * To check if a student is approved by the admin for registration using SQL queries.
	 * @param userId
	 * @return
	 */
	public boolean isApproved(int userId);
	
	/**
	 * To search a student by ID in database using SQL queries.
	 * @param userId
	 * @return
	 */
	public boolean searchStudent(long userId);
	
	/**
	 * to check if student has been registered or not using SQL queries.
	 * @param studentId
	 * @return
	 */
	public int isStudentRegistered(int studentId);
	
	/**
	 * To get count of students in a particular course using SQL queries.
	 * @param courseid
	 * @return
	 */
	public int getStudentCourseCount(int courseid);
	
	/**
	 * To get count of registered courses of a specific course using SQL queries.
	 * @param studentId
	 * @return
	 */
	public int getCourseCount(long studentId);
	
}
