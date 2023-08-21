/**
 * 
 */
package com.wibmo.business;

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
	 * @throws DuplicateCourseEntryException
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
	public int dropCourses(long studentId, int courseId) throws CourseNotFoundException,UserNotFoundException;
	
	
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
	 * @throws UserNotFoundException
	 */
	public void viewReportCard(int studId) throws UserNotApprovedException;

	/**
	 * To check if a student is registered of not.
	 * @param userId
	 * @return True if student is registered else returns False.
	 */
	public boolean isApproved(int userId) ;
	
}
		