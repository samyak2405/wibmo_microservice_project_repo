/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wibmo.entity.PaymentStudentMapper;
import com.wibmo.entity.Student;

/**
 * 
 */
public interface PaymentStudentMapperRepository extends CrudRepository<PaymentStudentMapper, Integer>{

	public List<PaymentStudentMapper> findByStudent(Student student);
}
