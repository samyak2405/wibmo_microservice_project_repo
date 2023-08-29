/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	


	@Query(value="SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE userId=?1", nativeQuery=true)
	public int getCourseCount(@Param("studentId")int studentId);
	
	@Modifying
	@Query(value=SQLConstants.DELETE_COURSE, nativeQuery =  true)
	public void dropCourses(@Param("studentId")int studentId,@Param("courseId")int courseId);

	
	@Query(value=SQLConstants.COURSE_PREFERENCE, nativeQuery = true )
	public int findCoursePreference(@Param("studentId")int studentId,@Param("courseId") int courseId);

	
	@Query(value="SELECT COUNT(*) FROM gradecard where student_userId=?1", nativeQuery = true)
	public int isApproved(@Param("student_userId")int studentId);

	@Query(value=SQLConstants.STUDENT_BY_EMAIL, nativeQuery =  true)
	public int findByEmail(@Param("userEmail")String userEmail);

	@Query(value=SQLConstants.SELECT_STUDENTID, nativeQuery =  true)
	public List<Integer> getStudentIds();

	@Query(value="SELECT COUNT(*) FROM studentcoursemapping WHERE userId=:studentId", nativeQuery = true)
	public Integer isStudentRegistered(@Param("studentId")int studentId);

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

	
	@Query(value=SQLConstants.GRADE_CARD, nativeQuery =  true)
	public List<Object[]> viewReportCard(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.APPROVE_STUDENT,nativeQuery =  true)
	public void setApprovedStudentById(int id);
	
	
}
