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
 * NotificationStudentMapping repository related to NotificationStudentMapping
 * CRUD operations
 */

@Repository
public interface NotificationStudentMappingRepository extends CrudRepository<NotificationStudentMapping, Integer> {
	/**
	 * returns list of notifications mapped with particular student object
	 * 
	 * @param student
	 * @return list of notifications
	 */
	public List<NotificationStudentMapping> findByStudent(Student student);
}
