package com.wibmo.service;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wibmo.dto.RegisterUserDto;
import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.Professor;
import com.wibmo.entity.Student;
import com.wibmo.entity.User;
import com.wibmo.exception.*;

/**
 * Professor Service Interface
 */
@Service
public interface ProfessorOperation {

	/**
	 * To set the grades of a student
	 * 
	 * @param studentId
	 * @param courseId
	 * @param grade
	 * @throws UserNotFoundException
	 * @throws CourseNotFoundException
	 */
	public void setGrades(int professorId, int studentId, String courseId, String grade)
			throws UserNotFoundException, CourseNotFoundException, CourseNotAssignedException;

	/**
	 * To request to teach a specific course
	 * 
	 * @param professorid
	 * @param courseIdList
	 * @throws CourseNotFoundException
	 */
	public void requestCourseOffering(int professorid, List<String> courseIdList)
			throws CourseNotFoundException, UserNotFoundException;

	/**
	 * To view list of registered student in a specific course.
	 * 
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return List<Student> for particular course with courseId under professor
	 *         with professorId
	 */
	public List<Student> viewStudentList(int professorId, String courseId)
			throws CourseNotFoundException, CourseNotAssignedException;

	/**
	 * To view the list of all the courses
	 * 
	 * @return List<CourseCatalog> containing list of courses
	 */
	public List<CourseCatalog> viewCourseCatalog();

	/**
	 * For signing up a new professor
	 * 
	 * @param user
	 * @throws UserAlreadyExistsException
	 */ 
	public void registerProfessor(RegisterUserDto user) throws UserAlreadyExistsException;

	/**
	 * Find professor using Email
	 * 
	 * @param userEmail
	 * @return count with get professor by id
	 */
	public int getProfessorById(String userEmail);

	/**
	 * Get the list of Assigned courses to professor
	 * 
	 * @param userId
	 * @return Map<Integer,String> containing CourseId and CourseName
	 * @throws UserNotFoundException
	 */
	public Map<Integer, String> listOfApprovedCourses(int userId) throws UserNotFoundException;

	public List<Professor> viewProfessor();

}
