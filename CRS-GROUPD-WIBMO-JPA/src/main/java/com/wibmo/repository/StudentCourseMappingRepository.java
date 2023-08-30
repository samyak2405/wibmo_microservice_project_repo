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
 * Repository related to student course mapping
 */
@Repository
public interface StudentCourseMappingRepository extends CrudRepository<StudentCourseMap, Integer>  {

/**
 * to get list of studentcoursemapping by course catalog details
 * @param course
 * @return List<StudentCourseMap>
 */
	public List<StudentCourseMap> findByCourse(CourseCatalog course); 
}
