/**
 * 
 */
package com.wibmo.dao;

import java.util.List;

import com.wibmo.bean.Admin;
import com.wibmo.bean.User;

/**
 * To perform read and write operations in Database for admin operations.
 */
	public interface AdminDAO {
	
	/**
	 * For the admin to add the course if students in particular course are greater than 3
	 * @param courseId
	 */
	public void addCourse(long courseId);
	
	/**
	 * For the admin to drop the course if students in particular course are less than 3
	 * @param courseId
	 */
	public void dropCourse(long courseId);
	
	/**
	 * 	To approve student
	 * @param studentId
	 * @param courses
	 * @return boolean
	 */
	public boolean approveStudent(long studentId,List<Long> courses);
	
	/**
	 * 
	 * @param admin
	 */
	public void addAdmin(User admin);
	
	/**
	 * Assigning course to professor
	 * @param professorId
	 * @param courseId
	 */
	public void assignCoursesProf(int professorId,int courseId);
	
	/**
	 * To set grades
	 * @param studentId
	 * @param courseId
	 */
	public void setGradeCard(int studentId, int courseId);
	
	/**
	 * 
	 * @param courseId
	 * @return List 
	 */
	public List<Integer> getProfCourseData(int courseId);
	
	/**
	 * 
	 * @return List
	 */
	public List<Integer> getListOfCourses();
	
	/**
	 * 
	 * @param professorid
	 * @param courseid
	 */
	public void setProfCourse(int professorid, int courseid);
	
	/**
	 * 
	 * @param userId
	 * @return boolean
	 */
	public boolean searchAdmin(long userId);
	
	
	public void setApprovedStudents();
	
	/**
	 * 
	 * @return List
	 */
	public List<Integer> pendingRegistration();
	
	/**
	 * 
	 * @param studentId
	 */
	public void setApprovedStudentById(int studentId);
	
	/**
	 * 
	 * @param userEmail
	 * @return id
	 */

	public int getAdminById(String userEmail);
	
	/**
	 * 
	 * @return List of professorIds
	 */

	public List<Integer> getProfessorsIds();
	
	/**
	 * 
	 * @param professorId
	 * @return List
	 */

	public List<Integer> getProfessorCourses(int professorId);
	
	/**
	 * To approve course requested by professor
	 * @param professor
	 * @param course
	 */

	public void approveCourse(int professor, int course);
	
	/**
	 * 
	 * @param studentId
	 */

	public void setRejectionStatus(int studentId);
	
}
