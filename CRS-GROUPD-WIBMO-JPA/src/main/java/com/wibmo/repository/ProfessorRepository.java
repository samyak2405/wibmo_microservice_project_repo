/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Professor;
import com.wibmo.entity.Student;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface ProfessorRepository extends CrudRepository<Professor,Integer>{
	
  	@Modifying
	@Query(value=SQLConstants.SET_GRADES, nativeQuery = true)
	public void setGrades(@Param("grade")String grade,@Param("studentId") int studentId,@Param("courseId") int courseId);
	
  	@Modifying
	@Query(value=SQLConstants.REQUEST_COURSE, nativeQuery = true)
	public void requestCourseOffering(@Param("professorId")int professorid,@Param("courseId")int courseId);
	
  	@Modifying
	@Query(value=SQLConstants.STUDENT_LIST, nativeQuery = true)
	public Optional<List<Student>> findStudentByCourseId(@Param("courseId")int courseId);
	
  	
	@Query(value=SQLConstants.SEARCH_PROFESSOR, nativeQuery =  true)
	public int findProfessorByEmail(@Param("userEmail")String userEmail);
	
  	@Modifying
	@Query(value=SQLConstants.SELECT_PROFESSOR_BY_EMAIL, nativeQuery =  true)
	public int getProfessorById(@Param("userEmail")String userEmail);

  	@Modifying
  	@Query(value=SQLConstants.LIST_APPROVED_COURSES, nativeQuery = true)
	public List<Object[]> listOfApprovedCourses(@Param("professorId") int userId);
	
}
