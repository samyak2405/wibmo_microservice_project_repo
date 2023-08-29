/**
 * 
 */
package com.wibmo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.*;

/**
 * 
 */
@Repository
public interface AuthenticationRepository extends CrudRepository<User,Integer> {
	@Modifying
    @Query(value=SQLConstants.VERIFY_STUDENT,nativeQuery=true)
	public Student studentLoggedin(@Param("userEmail") String userEmail);
	@Modifying
    @Query(value=SQLConstants.VERIFY_PROFESSOR,nativeQuery=true)
	public Professor professorLoggedin(@Param("userEmail") String userEmail);
	@Modifying
    @Query(value=SQLConstants.VERIFY_ADMIN,nativeQuery=true)
	public Admin adminLoggedin(@Param("userEmail") String userEmail);
	@Modifying
    @Query(value=SQLConstants.UPDATE_PASSWORD_STUDENT,nativeQuery=true)
	public void updateStudentPassword(@Param("userEmail") String userEmail,String userPassword);
	@Modifying
    @Query(value=SQLConstants.UPDATE_PASSWORD_PROFESSOR,nativeQuery=true)
	public void updateProfessorPassword(@Param("userEmail") String userEmail,String userPassword);
	@Modifying
    @Query(value=SQLConstants.UPDATE_PASSWORD_ADMIN,nativeQuery=true)
	public void updateAdminPassword(@Param("userEmail") String userEmail,String userPassword);
//	User findByUseremail(String useremail);

	

}
