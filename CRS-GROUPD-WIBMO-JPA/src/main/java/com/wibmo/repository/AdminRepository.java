/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface AdminRepository extends CrudRepository<User,Integer> {


	@Modifying
	@Query(value=SQLConstants.APPROVE_STUDENT,nativeQuery=true)
	public void approveStudentRegistration();

	@Modifying
	@Query(value=SQLConstants.APPROVE_STUDENT_BY_ID,nativeQuery=true)
	public void approveStudentRegistrationById(@Param("userId")int userId);

	@Modifying
	@Query(value=SQLConstants.SELECT_PROFESSORS_BY_ID,nativeQuery=true)
	public List<Integer> getProfessorsIds();

	@Modifying
	@Query(value=SQLConstants.SELECT_PROFESSOR_COURSES,nativeQuery=true)
	public List<Integer> getProfessorCourses(@Param("professorId")int professorId);

	@Modifying
	@Query(value=SQLConstants.APPROVE_PROFESSOR_COURSE,nativeQuery=true)
	public void approveCourse(@Param("professorId")int professor,@Param("courseId")int courseId);

	@Modifying
	@Query(value=SQLConstants.SEARCH_ADMIN,nativeQuery=true)
	public int getAdminById(@Param("userEmail")String userEmail);

	@Modifying
	@Query(value=SQLConstants.INSERT_GRADECARD,nativeQuery=true)
	public void setGradeCard(@Param("studentId")int studentId,@Param("courseId") int courseId);

	@Modifying
	@Query(value=SQLConstants.STUDENT_REGISTRATION_REJECTION,nativeQuery=true)
	public void setRejectionStatus(@Param("studentId")int studentId);
	

	
	
}
