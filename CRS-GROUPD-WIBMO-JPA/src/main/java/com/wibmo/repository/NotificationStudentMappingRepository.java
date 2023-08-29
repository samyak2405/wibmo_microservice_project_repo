/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.NotificationStudentMapping;
import com.wibmo.entity.Student;

import java.util.List;


/**
 * 
 */
@Repository
public interface NotificationStudentMappingRepository extends CrudRepository<NotificationStudentMapping,Integer>
{
	public List<NotificationStudentMapping> findByStudent(Student student);
}
