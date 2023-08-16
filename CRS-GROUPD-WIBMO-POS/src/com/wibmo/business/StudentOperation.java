/**
 * 
 */
package com.wibmo.business;

import java.util.List;
import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.GradeCard;
import com.wibmo.bean.StudentCourseMap;
import com.wibmo.bean.User;
import com.wibmo.exception.*;

/**
 * 
 */
public interface StudentOperation {
	
	
	/**
	 * 
	 */
	public void registerCourses();
	
	/**
	 * To add new course for registration
	 * @param studCoMap
	 * @throws DuplicateCourseEntryException
	 */
	public void addCourses(StudentCourseMap studCoMap) throws DuplicateCourseEntryException;
	
	/**
	 * To drop course from registration list
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws NoCourseAvailableException
	 * @throws StudentNotFoundException
	 */
	public int dropCourses(long studentId, int courseId) throws NoCourseAvailableException,StudentNotFoundException;
	
	
	/**
	 * To view list of already registered courses
	 */
	public void listCourse();
	
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
	 * @throws StudentNotFoundException
	 */
	public void viewReportCard(int studId)throws StudentNotFoundException;
	
}
		