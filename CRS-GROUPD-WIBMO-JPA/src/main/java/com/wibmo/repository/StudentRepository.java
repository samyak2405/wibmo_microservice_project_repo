/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;
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
	
public interface StudentRepository extends CrudRepository<User,Integer> {

	@Query(value=SQLConstants.UPDATE_REGISTER)
	public void registerCourses(int studentId);
	
	@Query(value=SQLConstants.ADD_COURSES)
	public void AddSingleCourse(int studentId,int courseId,int coursePref);
	
	@Query(value=SQLConstants.COUNT_COURSES)
	public int getCourseCount(int studentId);
	
	@Query(value=SQLConstants.DELETE_COURSE)
	public void dropCourses(int studentId,int courseId);

	@Query(value=SQLConstants.COURSE_PREFERENCE)
	public int findCoursePreference(int studentId, int courseId);

	@Query(value=SQLConstants.IS_APPROVED)
	public int isApproved(int studentId);

	@Query(value=SQLConstants.STUDENT_BY_EMAIL)
	public String findByEmail(String userEmail);

}
