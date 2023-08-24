/**
 * 
 */
package com.wibmo.business;

import java.util.Map;

import com.wibmo.bean.StudentCourseMap;
import com.wibmo.bean.User;
import com.wibmo.exception.*;

/**
 * For performing various student operations
 */
public interface StudentOperation {
	
	
	/**
	 * 
	 */
	public void registerCourses(int studentId);
	
	/**
	 * To add new course for registration
	 * @param studCoMap
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceededException
	 */
	public void addCourses(StudentCourseMap studCoMap) throws CourseNotFoundException,CourseLimitExceededException ;
	
	/**
	 * To drop course from registration list
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException
	 */
	public int dropCourses(int studentId, int courseId) throws CourseNotFoundException,UserNotFoundException;
	
	
	/**
	 * To view list of already registered courses
	 */
	public void listCourse(int studentId) throws UserNotApprovedException;
	
	/**
	 * To view the list of offered courses
	 */
	public void viewCourseCatalog();
	
	
	/**
	 * To register a new Student user.
	 * @param user
	 * @throws StudentAlreadyRegisteredException
	 */
	public void registerStudent(User user) throws StudentAlreadyRegisteredException;
	

	/**
	 * To view the Grade card of all the courses of a student.
	 * @param studId
	 * @throws UserNotApprovedException
	 */
	public void viewReportCard(int studId) throws UserNotApprovedException;

	/**
	 * To check if a student is registered of not.
	 * @param userId
	 * @return True if student is registered else returns False.
	 */
	public boolean isApproved(int userId) ;

	
	/**
	 * To get id of student
	 * @param userEmail
	 * @return id
	 */
	public int getStudentByEmail(String userEmail);
     
	/**
	 * To get list of  courseId,CoursePreference registered by specific student
	 * @param userId
	 * @return map of courseId,course Preference
	 */
	public Map<Integer, String> getAddedCourses(int userId);
	
	/**
	 * To know if student is registered or not
	 * @param userId
	 * @return 1 if student is registered and 0 if student is not registered
	 */
	public int isStudentRegistered(int userId);
	
	/**
	 
	 * @param studentId
	 * @param courseId
	 * @param coursePref
	 */
	public void AddSingleCourse(int studentId,int courseId,int coursePref);
	
}
		