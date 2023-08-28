/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wibmo.entity.ProfessorCourseMap;
import com.wibmo.constant.*;

/**
 * 
 */
public interface ProfessorCourseMappingRepository extends CrudRepository<ProfessorCourseMap, Long> {
	
	
	@Query(value = SQLConstants.SELECT_PROFESSORS_BY_ID,nativeQuery = true)
	public List<Integer> listProfessorIds();
	
	@Query(value=SQLConstants.SELECT_PROFESSOR_COURSES, nativeQuery = true)
	public List<Integer>getProfessorCourses(int professorId);
	
	@Modifying
	@Query(value=SQLConstants.APPROVE_PROFESSOR_COURSE,nativeQuery = true)
	public void approveCourseProf(int professorId, int courseId);
	
}
