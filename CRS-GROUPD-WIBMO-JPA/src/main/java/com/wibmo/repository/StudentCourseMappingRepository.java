/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.StudentCourseMap;

/**
 * 
 */
@Repository
public interface StudentCourseMappingRepository extends CrudRepository<StudentCourseMap, Integer>  {

	public List<StudentCourseMap> findByCourse(CourseCatalog course); 
}
