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
import com.wibmo.entity.Student;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface ProfessorRepository extends CrudRepository<User,Integer>{
	
  	@Modifying
	@Query(value=SQLConstants.SET_GRADES)
	public void setGrades(@Param("grade")String grade,@Param("studentId") int studentId,@Param("courseId") int courseId);
	
  	@Modifying
	@Query(value=SQLConstants.REQUEST_COURSE)
	public void requestCourseOffering(@Param("professorId")int professorid,@Param("courseId")int courseId);
	
  	@Modifying
	@Query(value=SQLConstants.STUDENT_LIST)
	public Optional<List<Student>> findStudentByCourseId(@Param("courseId")int courseId);
	
  	@Modifying
	@Query(value=SQLConstants.SEARCH_PROFESSOR)
	public int findProfessorByEmail(@Param("userEmail")String userEmail);
	
  	@Modifying
	@Query(value=SQLConstants.SELECT_PROFESSOR_BY_EMAIL)
	public int getProfessorById(@Param("userEmail")String userEmail);

  	@Modifying
  	@Query(value=SQLConstants.LIST_APPROVED_COURSES)
	public List<Object[]> listOfApprovedCourses(int userId);
	
}
