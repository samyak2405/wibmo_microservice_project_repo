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
	@Query("SELECT COUNT(*) FROM crs.student WHERE studentemail=?")
	public int doesEmailExist(@Param("studentemail") String studentemail);

	@Modifying
	@Query(value=SQLConstants.UPDATE_REGISTER)
	public void registerCourses(@Param("studentId")int studentId);
	
	@Modifying
	@Query(value=SQLConstants.ADD_COURSES)
	public void AddSingleCourse(@Param("studentId")int studentId,@Param("courseId")int courseId,@Param("courseId")int coursePref);
	
	@Modifying
	@Query(value=SQLConstants.COUNT_COURSES)
	public int getCourseCount(int studentId);
	
	@Modifying
	@Query(value=SQLConstants.DELETE_COURSE)
	public void dropCourses(@Param("studentId")int studentId,@Param("courseId")int courseId);

	@Modifying
	@Query(value=SQLConstants.COURSE_PREFERENCE)
	public int findCoursePreference(@Param("studentId")int studentId,@Param("courseId") int courseId);

	@Modifying
	@Query(value=SQLConstants.IS_APPROVED)
	public int isApproved(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.STUDENT_BY_EMAIL)
	public int findByEmail(@Param("userEmail")String userEmail);

	@Modifying
	@Query(value=SQLConstants.SELECT_STUDENTID)
	public List<Integer> getStudentIds();

	@Modifying
	@Query(value=SQLConstants.SELECT_REGISTER)
	public int isStudentRegistered(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.IS_APPROVED)
	public int isCourseRegistrationApproved(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.SELECT_COURSEMAPPING)
	public List<Object[]> getStudentCourseData(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.COUNT_STUDENT_COURSES)
	public int getStudentCourseCount(@Param("courseId")Integer courseId);

	@Modifying
	@Query(value=SQLConstants.LIST_STUDENT_REG_COURSES)
	public List<Object[]> listCourse(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.SELECT_ADDED_COURSE)
	public List<Object[]> getAddedCourses(@Param("userId")int userId);

	@Modifying
	@Query(value=SQLConstants.GRADE_CARD)
	public List<Object[]> viewReportCard(@Param("studentId")int studentId);

	
}
