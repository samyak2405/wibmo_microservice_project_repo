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

import com.wibmo.entity.ProfessorCourseMap;
import com.wibmo.constant.*;

/**
 * 
 */
@Repository
public interface ProfessorCourseMappingRepository extends CrudRepository<ProfessorCourseMap, Long> {
	
	
	@Query(value = "SELECT DISTINCT userId FROM professorcoursemapping",nativeQuery = true)
	public List<Integer> listProfessorIds();
	
	@Query(value="SELECT courseId FROM professorcoursemapping WHERE userId=?1", nativeQuery = true)
	public List<Integer> getProfessorCourses(@Param("userId")int professorId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE professorcoursemapping SET isApproved=1 WHERE userId=?1 AND courseId=?2",nativeQuery = true)
	public void approveCourseProf(@Param("userId")int professorId,@Param("courseId") int courseId);
	
}
