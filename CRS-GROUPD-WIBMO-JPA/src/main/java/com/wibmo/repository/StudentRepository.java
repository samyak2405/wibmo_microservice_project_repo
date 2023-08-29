/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wibmo.entity.Student;
import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
	@Modifying
	@Query(value=SQLConstants.UPDATE_REGISTER, nativeQuery = true)
	public void registerCourses(@Param("studentId")int studentId);
	
	@Modifying
	@Query(value=SQLConstants.ADD_COURSES, nativeQuery = true)
	public void AddSingleCourse(@Param("studentId")int studentId,@Param("courseId")int courseId,@Param("coursecategory")int coursePref);
	
	@Modifying
	@Query(value=SQLConstants.COUNT_COURSES, nativeQuery=true)
	public int getCourseCount(@Param("studentId")int studentId);
	
	@Modifying
	@Query(value=SQLConstants.DELETE_COURSE, nativeQuery =  true)
	public void dropCourses(@Param("studentId")int studentId,@Param("courseId")int courseId);

	@Modifying
	@Query(value=SQLConstants.COURSE_PREFERENCE, nativeQuery = true )
	public int findCoursePreference(@Param("studentId")int studentId,@Param("courseId") int courseId);

	@Modifying
	@Query(value=SQLConstants.IS_APPROVED, nativeQuery = true)
	public int isApproved(@Param("studentId")int studentId);

	@Query(value=SQLConstants.STUDENT_BY_EMAIL, nativeQuery =  true)
	public int findByEmail(@Param("userEmail")String userEmail);

	@Query(value=SQLConstants.SELECT_STUDENTID, nativeQuery =  true)
	public List<Integer> getStudentIds();

	@Query(value=SQLConstants.SELECT_REGISTER, nativeQuery = true)
	public int isStudentRegistered(@Param("studentId")int studentId);

	@Query(value=SQLConstants.IS_APPROVED, nativeQuery =  true)
	public int isCourseRegistrationApproved(@Param("studentId")int studentId);

	@Query(value=SQLConstants.SELECT_COURSEMAPPING, nativeQuery =  true)
	public List<Object[]> getStudentCourseData(@Param("studentId")int studentId);

	@Query(value=SQLConstants.COUNT_STUDENT_COURSES, nativeQuery =  true)
	public int getStudentCourseCount(@Param("courseId")Integer courseId);

	@Query(value=SQLConstants.LIST_STUDENT_REG_COURSES, nativeQuery = true)
	public List<Object[]> listCourse(@Param("studentId")int studentId);

	@Query(value=SQLConstants.SELECT_ADDED_COURSE, nativeQuery =  true)
	public List<Object[]> getAddedCourses(@Param("userId")int userId);

	@Modifying
	@Query(value=SQLConstants.GRADE_CARD, nativeQuery =  true)
	public List<Object[]> viewReportCard(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.APPROVE_STUDENT,nativeQuery =  true)
	public void setApprovedStudentById(int id);
	
	
}
