/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
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
  @Query("SELECT COUNT(*) FROM crs.professor WHERE professoremail=?")
  public int searchProfessor(@Param("professoremail") String professoremail);
	
	@Query(value=SQLConstants.SET_GRADES)
	public void setGrades(String grade, int studentId, int courseId);
	
	@Query(value=SQLConstants.REQUEST_COURSE)
	public void requestCourseOffering(int professorid,int courseId);
	
	@Query(value=SQLConstants.STUDENT_LIST)
	public Optional<List<Student>> findStudentByCourseId(int courseId);
	
	@Query(value=SQLConstants.SEARCH_PROFESSOR)
	public int findProfessorByEmail(String userEmail);
	
	@Query(value=SQLConstants.SELECT_PROFESSOR_BY_EMAIL)
	public int getProfessorById(String userEmail);
	
}
