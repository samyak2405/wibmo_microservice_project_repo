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
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
  	@Modifying
	@Query(value="INSERT INTO professorcoursemapping VALUES(?1,?2,0)", nativeQuery = true)
	public void requestCourseOffering(@Param("userId")int professorid,@Param("courseId")int courseId);
	
  
	@Query(value="SELECT userId,userName, userEmail, userPhonenumber FROM student WHERE userId IN "
			+ "(SELECT userId FROM studentcoursemapping WHERE courseId=?1)", nativeQuery = true)
	public Optional<List<Student>> findStudentByCourseId(@Param("courseId")int courseId);
	
  
	@Query(value=SQLConstants.SEARCH_PROFESSOR, nativeQuery =  true)
	public int findProfessorByEmail(@Param("userEmail")String userEmail);
	
  
	@Query(value=SQLConstants.SELECT_PROFESSOR_BY_EMAIL, nativeQuery =  true)
	public int getProfessorById(@Param("userEmail")String userEmail);

  	
  	@Query(value="SELECT pcm.courseid, cc.courseName FROM professorcoursemapping pcm INNER JOIN coursecatalog cc ON pcm.courseid=cc.courseId WHERE pcm.userId=?1 AND pcm.isApproved=1", nativeQuery = true)
	public List<Object[]> listOfApprovedCourses(@Param("professorId") int userId);
	
}
