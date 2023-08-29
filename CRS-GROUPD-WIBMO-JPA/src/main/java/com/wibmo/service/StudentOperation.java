/**
 * 
 */
package com.wibmo.service;

import java.util.List;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.wibmo.dto.AddCourseDto;
import com.wibmo.dto.GradeCardResponseDTO;
import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.entity.User;
import com.wibmo.exception.*;

/**
 * For performing various student operations
 */
@Service
@Transactional
public interface StudentOperation {
	
	
	/**
	 * 
	 */
	public void registerCourses(int studentId) throws UserNotFoundException;
	
	/**
	 * To add new course for registration
	 * @param studCoMap
	 * @throws DuplicateCourseEntryException
	 */
	@Transactional
	public void addCourses(int userId,AddCourseDto addCourseDto) throws CourseNotFoundException,CourseLimitExceededException,UserNotFoundException ;
	
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
	 * To get a list of registered course.
	 * @param studentId
	 * @return a map of registered courseId and their name.
	 * @throws UserNotApprovedException
	 */
	public Map<Integer,String> listCourse(int studentId) throws UserNotApprovedException;
	
	/**
	 * To view the list of offered courses
	 */
	public List<CourseCatalog> viewCourseCatalog();
	
	
	/**
	 * To register a new Student user.
	 * @param user
	 * @throws StudentAlreadyRegisteredException
	 */
	@Transactional
	public void registerStudent(User user) throws StudentAlreadyRegisteredException;
	

	/**
	 * To view the Grade card of all the courses of a student.
	 * @param studId
	 * @throws UserNotFoundException
	 */
	public GradeCardResponseDTO viewReportCard(int studId) throws UserNotApprovedException;

	/**
	 * To check if a student is registered of not.
	 * @param userId
	 * @return True if student is registered else returns False.
	 */
	public int isApproved(int userId) ;

	public int getStudentByEmail(String userEmail);

	public Map<Integer, String> getAddedCourses(int userId);
	public Integer isStudentRegistered(int userId);
	
}
		