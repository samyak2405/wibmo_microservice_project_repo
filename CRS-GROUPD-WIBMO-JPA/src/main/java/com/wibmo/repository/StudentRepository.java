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
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
	@Modifying
	@Query("SELECT COUNT(*) FROM crs.student WHERE studentemail=?")
	public int doesEmailExist(@Param("studentemail") String studentemail);
	
}
