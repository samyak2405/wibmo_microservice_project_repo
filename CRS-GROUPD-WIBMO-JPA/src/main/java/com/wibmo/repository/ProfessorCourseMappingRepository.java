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
	 * returns list of distinct professor Ids related to professor course mapping
	 * 
	 * @return list of professor Id
	 */
	@Query(value = "SELECT DISTINCT userId FROM professorcoursemapping", nativeQuery = true)
	public List<Integer> listProfessorIds();

	/**
	 * returns list of courseIds related to professorId
	 * 
	 * @param professorId
	 * @return list of courses
	 */
	@Query(value = "SELECT courseId FROM professorcoursemapping WHERE userId=?1", nativeQuery = true)
	public List<Integer> getProfessorCourses(@Param("userId") int professorId);

	/**
	 * sets professorId and courseId in professorcoursemapper table
	 * 
	 * @param professorId
	 * @param courseId
	 */
	@Modifying
	@Transactional
	@Query(value="UPDATE professorcoursemapping SET isApproved=1 WHERE userId=?1 AND courseId=?2",nativeQuery = true)
	public void approveCourseProf(@Param("userId")int professorId,@Param("courseId") String courseId);
		/**
	 * returns the professorcoursemap related to professor and course
	 * 
	 * @param professor
	 * @param course
	 * @return professorcoursemap object
	 */
	public ProfessorCourseMap findByProfessorAndCourseCatalog(Professor professor, CourseCatalog course);

}
