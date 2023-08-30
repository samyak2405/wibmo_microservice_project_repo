/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Admin;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {

	
	@Modifying
	@Transactional
	@Query(value=SQLConstants.APPROVE_STUDENT,nativeQuery=true)
	public void approveStudentRegistration();
	
	@Transactional
	@Modifying
	@Query(value=SQLConstants.APPROVE_STUDENT_BY_ID,nativeQuery=true)
	public void approveStudentRegistrationById(@Param("userId")int userId);

	
	@Query(value=SQLConstants.SELECT_PROFESSORS_BY_ID,nativeQuery=true)
	public List<Integer> getProfessorsIds();

	
	@Query(value=SQLConstants.SELECT_PROFESSOR_COURSES,nativeQuery=true)
	public List<Integer> getProfessorCourses(@Param("professorId")int professorId);



	@Query(value=SQLConstants.SEARCH_ADMIN,nativeQuery=true)
	public int getAdminById(@Param("userEmail")String userEmail);

	@Modifying
	@Query(value=SQLConstants.STUDENT_REGISTRATION_REJECTION,nativeQuery=true)
	public void setRejectionStatus(@Param("studentId")int studentId);
	
	@Query(value="SELECT COUNT(*) FROM admin WHERE userEmail=?1", nativeQuery =  true)
	public int findByEmail(@Param("userEmail")String userEmail);

	
	
}
