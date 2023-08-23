package com.wibmo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wibmo.model.CourseCatalog;
/**
 * For performing various professor operations.
 */
import com.wibmo.model.User;
import com.wibmo.exception.*;

@Service
public interface ProfessorOperation {
	
	/**
	 * To set the grades of a student
	 * @param studentId
	 * @param courseId
	 * @param grade
	 * @throws UserNotFoundException
	 * @throws CourseNotFoundException
	 */
  public void setGrades(int studentId,int courseId,String grade) throws UserNotFoundException,CourseNotFoundException ;
  
  /**
   * To request to teach a specific course
   * @param professorid
   * @param courseIdList
   * @return
   * @throws CourseNotFoundException
   */
  public boolean requestCourseOffering(int professorid,List<Integer> courseIdList) throws CourseNotFoundException;
  
  /**
   * To view list of registered student in a specific course.
   * @param courseId
   * @throws CourseNotFoundException
   */
  public void viewStudentList(Integer courseId) throws CourseNotFoundException;
  
  /**
   * To view the list of all the courses
   */
  public  void viewCourseCatalog();
  
  
  /**
   * For signing up a new professor
   * @param user
   * @throws UserAlreadyExistsException
   */
  public void registerProfessor(User user)throws UserAlreadyExistsException;

public int getProfessorById(String userEmail);

public void listOfApprovedCourses(int userId);
  
}
