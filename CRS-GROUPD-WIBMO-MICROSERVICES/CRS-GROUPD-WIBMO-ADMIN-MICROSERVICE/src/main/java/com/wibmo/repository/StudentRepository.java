/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Student;
import com.wibmo.entity.User;

/**
 * Student repository related to student CRUD operations
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	/**
	 * returns the list student Ids.
	 * 
	 * @return list of studentIds
	 */
	@Query(value = SQLConstants.GET_STUDENT_IDS , nativeQuery = true)
	public List<Integer> getStudentIds();

	/**
	 * sets approval of student related to given studentID
	 * 
	 * @param id
	 */
	@Modifying
	@Transactional
	@Query(value = SQLConstants.APPROVE_STUDENT_BY_ID, nativeQuery = true)
	public void setApprovedStudentById(@Param("userId") int id);

	/**
	 * returns the count of registered students
	 * 
	 * @param studentId
	 * @return count of registered students
	 */
	@Query(value = SQLConstants.STUDENT_REGISTERED, nativeQuery = true)
	public Integer isStudentRegistered(@Param("studentId") int studentId);

	/**
	 * returns the status of approval of student registration
	 * 
	 * @param studentId
	 * @return course registration approval
	 */
	@Query(value = SQLConstants.COURSE_REGISTRATION_APPROVED , nativeQuery = true)
	public int isCourseRegistrationApproved(@Param("userId") int studentId);

	/**
	 * returns the list of custom objects containing details related to course ID
	 * and category
	 * 
	 * @param studentId
	 * @return list of custom objects
	 */
	@Query(value = SQLConstants.GET_STUDENT_COURSE_DATA, nativeQuery = true)
	public List<Object[]> getStudentCourseData(@Param("studentId") int studentId);

	/**
	 * return the count of courses
	 * 
	 * @param courseId
	 * @return coursecount
	 */
	@Query(value = SQLConstants.STUDENT_COURSE_COUNT, nativeQuery = true)
	public int getStudentCourseCount(@Param("courseId") String courseId);

	public User findByUserEmail(String userEmail);

}
