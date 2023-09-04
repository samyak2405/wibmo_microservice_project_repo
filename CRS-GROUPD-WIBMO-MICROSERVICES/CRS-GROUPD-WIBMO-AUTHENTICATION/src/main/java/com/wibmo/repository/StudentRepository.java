/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Student;


/**
 * Student repository related to student CRUD operations
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
	
	
/*
 * returns the count of student matching with given mail
 * @param userEmail
 * @return findByEmail
 */
	@Query(value=SQLConstants.STUDENT_BY_EMAIL, nativeQuery =  true)
	public int findByEmail(@Param("userEmail")String userEmail);
	

	
	
}
