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
	
    @Query(value="SELECT * FROM student WHERE userEmail=:userEmail",nativeQuery=true)
	public Student studentLoggedin(@Param("userEmail") String userEmail);
	
    @Query(value="SELECT * FROM professor WHERE userEmail=:userEmail",nativeQuery=true)
	public Professor professorLoggedin(@Param("userEmail") String userEmail);
	
    @Query(value="SELECT * FROM admin WHERE userEmail=:userEmail",nativeQuery=true)
	public Admin adminLoggedin(@Param("userEmail") String userEmail);
    
    
	@Modifying
    @Query(value="UPDATE student SET userPassword=?2 WHERE userEmail=?1",nativeQuery=true)
	public void updateStudentPassword(@Param("userEmail") String userEmail,String userPassword);
	@Modifying
    @Query(value="UPDATE professor SET userPassword=?2 WHERE userEmail=?1",nativeQuery=true)
	public void updateProfessorPassword(@Param("userEmail") String userEmail,String userPassword);
	@Modifying
    @Query(value="UPDATE admin SET userPassword=?2 WHERE userEmail=?1",nativeQuery=true)
	public void updateAdminPassword(@Param("userEmail") String userEmail,String userPassword);
//	User findByUseremail(String useremail);

	

}
