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

	

	public boolean isApproved(int userId);
	public boolean searchStudentByID(int studentId);
	public int isStudentRegistered(int studentId);
	public int getStudentCourseCount(int courseid);
	public int getCourseCount(long studentId);

	public int getStudentByEmail(String userEmail);

	public boolean doesEmailExist(String userEmail);

	public Map<Integer, String> getAddedCourses(int userId);
	
	public void AddSingleCourse(int studentId,int courseId,int coursePref);

	
}
