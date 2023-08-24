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
	 * 
	 * @param courseId
	 */
	public void addCourse(long courseId);
	
	/**
	 * 
	 * @param courseId
	 */
	public void dropCourse(long courseId);
	
	/**
	 * 	
	 * @param studentId
	 * @param courses
	 * @return
	 */
	public boolean approveStudent(long studentId,List<Long> courses);
	
	/**
	 * 
	 * @param admin
	 */
	public void addAdmin(User admin);
	
	/**
	 * 
	 * @param professorId
	 * @param courseId
	 */
	public void assignCoursesProf(int professorId,int courseId);
	
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 */
	public void setGradeCard(int studentId, int courseId);
	
	/**
	 * 
	 * @param courseId
	 * @return
	 */
	public List<Integer> getProfCourseData(int courseId);
	
	/**
	 * 
	 * @return
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
	 * @return
	 */
	public boolean searchAdmin(long userId);
	
	/**
	 * 
	 */
	public void setApprovedStudents();
	
	/**
	 * 
	 * @return
	 */
	public List<Integer> pendingRegistration();
	
	/**
	 * 
	 * @param studentId
	 */
	public void setApprovedStudentById(int studentId);

	public int getAdminById(String userEmail);

	public List<Integer> getProfessorsIds();

	public List<Integer> getProfessorCourses(int professorId);

	public void approveCourse(int professor, int course);

	public void setRejectionStatus(int studentId);
	
}
