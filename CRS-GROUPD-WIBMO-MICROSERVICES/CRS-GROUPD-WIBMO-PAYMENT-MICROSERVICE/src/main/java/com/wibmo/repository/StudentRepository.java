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
import com.wibmo.entity.User;


/**
 * Student repository related to student CRUD operations
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
	
	/**
	 * returns the status of approval
	 * @param studentId
	 * @return isApproved
	 */
		@Query(value=SQLConstants.IS_APPROVED, nativeQuery = true)
		public int isApproved(@Param("userId")int studentId);
	/**
	 * returns the count of student matching with given mail
	 * @param userEmail
	 * @return findByEmail
	 */
		@Query(value=SQLConstants.FIND_BY_EMAIL, nativeQuery =  true)
		public int findByEmail(@Param("userEmail")String userEmail);

	/**
	 * returns the count of registered students 
	 * @param studentId
	 * @return count of registered students
	 */
		@Query(value=SQLConstants.IS_STUDENT_REGISTERED, nativeQuery = true)
		public Integer isStudentRegistered(@Param("studentId")int studentId);
	public User findByUserEmail(String userEmail);

	
	
	
}
