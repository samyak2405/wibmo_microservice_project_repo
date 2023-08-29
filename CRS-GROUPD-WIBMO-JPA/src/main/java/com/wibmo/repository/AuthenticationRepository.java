/**
 * 
 */
package com.wibmo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

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
	
    @Query(value="SELECT * FROM User WHERE userEmail=:userEmail AND role=1",nativeQuery=true)
	public User studentLoggedin(@Param("userEmail") String userEmail);
	
    @Query(value="SELECT * FROM user WHERE userEmail=:userEmail AND role=2",nativeQuery=true)
	public User professorLoggedin(@Param("userEmail") String userEmail);
	
    @Query(value="SELECT * FROM user WHERE userEmail=:userEmail AND role=3",nativeQuery=true)
	public User adminLoggedin(@Param("userEmail") String userEmail);
    
    
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
