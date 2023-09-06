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
public interface StudentRepository extends CrudRepository<Student,Integer> {
/**
 * marks student as registered according to the studentId
 * @param studentId
 */
	@Modifying
	@Transactional
	@Query(value=SQLConstants.IS_REGISTER, nativeQuery = true)
	public void registerCourses(@Param("studentId")int studentId);
	

/**
 * returns the count of courses from mapping table according to given studentId
 * @param studentId
 * @return courseCount
 */
	@Query(value=SQLConstants.COURSE_COUNT, nativeQuery=true)
	public int getCourseCount(@Param("userId")int studentId);
	
/**
 * delete the entry of student according to studentId and courseId specified
 * @param studentId
 * @param courseId
 */
	@Modifying
	@Query(value=SQLConstants.DELETE, nativeQuery =  true)
	public void dropCourses(@Param("userId")int studentId,@Param("courseId")String courseId);

/**
 * return the status that is student registered or not for a particular course
 * @param studentId
 * @param courseId
 * @return coursePreference
 */
	@Query(value=SQLConstants.SELECT_COURSE_CATEGORY, nativeQuery = true )
	public Integer findCoursePreference(@Param("studentId")int studentId,@Param("courseId") String courseId);

/**
 * returns the status of approval
 * @param studentId
 * @return isApproved
 */
	@Query(value=SQLConstants.IS_APPROVED, nativeQuery = true)
	public int isApproved(@Param("userId")int studentId);
/**
 * returns the count of student matching with given mail
 * @param userEmail
 * @return findByEmail
 */
	@Query(value=SQLConstants.FIND_BY_EMAIL, nativeQuery =  true)
	public int findByEmail(@Param("userEmail")String userEmail);

/**
 * returns the count of registered students 
 * @param studentId
 * @return count of registered students
 */
	@Query(value=SQLConstants.IS_STUDENT_REGISTERED, nativeQuery = true)
	public Integer isStudentRegistered(@Param("studentId")int studentId);

/**
 * returns the list of custom object containing details related to listing of course
 * @param studentId
 * @return list of custom object
 */
	@Query(value=SQLConstants.LIST_COURSE, nativeQuery = true)
	public List<Object[]> listCourse(@Param("studentId")int studentId);
/**
 * returns the list of object which contain details related to course and student mapping details
 * @param userId
 * @return list of custom object
 */
	@Query(value=SQLConstants.GET_ADDEDCOURSES, nativeQuery =  true)
	public List<Object[]> getAddedCourses(@Param("userId")int userId);


public User findByUserEmail(String userEmail);


	
}
