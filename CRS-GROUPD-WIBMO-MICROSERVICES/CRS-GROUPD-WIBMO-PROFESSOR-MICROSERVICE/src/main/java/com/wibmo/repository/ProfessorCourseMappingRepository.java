/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.Professor;
import com.wibmo.entity.ProfessorCourseMap;

/**
 * 
 */
@Repository
public interface ProfessorCourseMappingRepository extends CrudRepository<ProfessorCourseMap, Long> {

	
	/**
	 * returns the professorcoursemap related to professor and course
	 * 
	 * @param professor
	 * @param course
	 * @return professorcoursemap object
	 */
	public ProfessorCourseMap findByProfessorAndCourseCatalog(Professor professor, CourseCatalog course);

}
