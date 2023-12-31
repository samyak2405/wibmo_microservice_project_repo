/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.Student;
import com.wibmo.entity.User;


/**
 * Student repository related to student CRUD operations
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

	User findByUserEmail(String userEmail);

}
