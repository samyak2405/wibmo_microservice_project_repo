/**
 * 
 */
package com.wibmo.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.wibmo.model.CourseCatalog;
import com.wibmo.model.GradeCard;
import com.wibmo.model.Student;
import com.wibmo.model.StudentCourseMap;

/**
 * 
 */

@Repository
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
	public boolean searchStudentByID(int studentId);
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

	public int getStudentByEmail(String userEmail);

	public boolean doesEmailExist(String userEmail);

	public Map<Integer, String> getAddedCourses(int userId);
	
	public void AddSingleCourse(int studentId,int courseId,int coursePref);

	
}
