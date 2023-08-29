/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.StudentCourseMap;

/**
 * 
 */
@Repository
public interface StudentCourseMappingRepository extends CrudRepository<StudentCourseMap, Integer>  {

}
