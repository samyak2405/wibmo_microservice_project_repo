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

import com.wibmo.entity.Student;


/**
 * Student repository related to student CRUD operations
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

}
