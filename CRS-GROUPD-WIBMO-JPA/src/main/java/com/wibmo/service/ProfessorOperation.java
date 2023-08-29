package com.wibmo.service;

import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.Student;
/**
 * For performing various professor operations.
 */
import com.wibmo.entity.User;
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
  public void setGrades(int professorId, int studentId,int courseId,String grade) throws UserNotFoundException,CourseNotFoundException,CourseNotAssignedException ;
  
  /**
   * To request to teach a specific course
   * @param professorid
   * @param courseIdList
   * @throws CourseNotFoundException
   */
  public void requestCourseOffering(int professorid,List<Integer> courseIdList) throws CourseNotFoundException,UserNotFoundException ;
  
  /**
   * To view list of registered student in a specific course.
   * @param courseId
   * @throws CourseNotFoundException
   */
  public List<Student> viewStudentList(int professorId,Integer courseId) throws CourseNotFoundException,CourseNotAssignedException;
  
  /**
   * To view the list of all the courses
   */
  public  List<CourseCatalog> viewCourseCatalog();
  
  
  /**
   * For signing up a new professor
   * @param user
   * @throws UserAlreadyExistsException
   */
  public void registerProfessor(User user)throws UserAlreadyExistsException;

public int getProfessorById(String userEmail);

public Map<Integer,String> listOfApprovedCourses(int userId) throws UserNotFoundException;
  
}
