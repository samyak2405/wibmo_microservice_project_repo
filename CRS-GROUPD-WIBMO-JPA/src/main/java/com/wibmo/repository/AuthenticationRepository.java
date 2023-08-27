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

import com.wibmo.entity.Student;
import com.wibmo.entity.*;

/**
 * 
 */
@Repository
public interface AuthenticationRepository extends CrudRepository<User,Integer> {
	@Modifying
    @Query("SELECT student FROM crs.student WHERE studentemail=?")
	public Student studentLoggedin(@Param("studentemail") String userEmail);
	@Modifying
    @Query("SELECT professor FROM crs.professor WHERE professoremail=?")
	public Professor professorLoggedin(@Param("professor") String userEmail);
	@Modifying
    @Query("SELECT admin FROM crs.admin WHERE adminEmail=?")
	public Admin adminLoggedin(@Param("adminEmail") String userEmail);
	@Modifying
    @Query("UPDATE crs.student SET password=? WHERE studentemail=?")
	public Admin updateStudentPassword(@Param("studentemail") String userEmail,String password);
	@Modifying
    @Query("UPDATE crs.professor SET password=? WHERE professoremail=?")
	public Admin updateProfessorPassword(@Param("professoremail") String userEmail,String password);
	@Modifying
    @Query("UPDATE crs.student SET password=? WHERE adminEmail=?")
	public Admin updateAdminPassword(@Param("adminEmail") String userEmail,String password);
	

}
