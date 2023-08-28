/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.Student;
import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.*;

/**
 * 
 */
@Repository
public interface AuthenticationRepository extends CrudRepository<User,Integer> {
	@Modifying
    @Query(value=SQLConstants.VERIFY_STUDENT)
	public Student studentLoggedin(@Param("userEmail") String userEmail);
	@Modifying
    @Query(value=SQLConstants.VERIFY_PROFESSOR)
	public Professor professorLoggedin(@Param("userEmail") String userEmail);
	@Modifying
    @Query(value=SQLConstants.VERIFY_ADMIN)
	public Admin adminLoggedin(@Param("userEmail") String userEmail);
	@Modifying
    @Query(value=SQLConstants.UPDATE_PASSWORD_STUDENT)
	public Admin updateStudentPassword(@Param("userEmail") String userEmail,String userPassword);
	@Modifying
    @Query(value=SQLConstants.UPDATE_PASSWORD_PROFESSOR)
	public Admin updateProfessorPassword(@Param("userEmail") String userEmail,String userPassword);
	@Modifying
    @Query(value=SQLConstants.UPDATE_PASSWORD_ADMIN)
	public Admin updateAdminPassword(@Param("userEmail") String userEmail,String userPassword);
	

}
