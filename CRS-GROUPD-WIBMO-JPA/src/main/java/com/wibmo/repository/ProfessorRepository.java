/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Professor;


/**
 * Repository related to professor crud operations
 */
@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

	/**
	 * sets the grade in gradecard table related to particular student and subject
	 * 
	 * @param grade
	 * @param studentId
	 * @param courseId
	 */
	@Modifying
	@Query(value = "UPDATE gradecard SET grade=?1 WHERE userId=?2 AND courseId=?3", nativeQuery = true)
	public void setGrades(@Param("grade") String grade, @Param("userId") int studentId,
			@Param("courseId") int courseId);

	/**
	 * returns list of object where each object is having student details
	 * 
	 * @param courseId
	 * @return list of custom objects
	 */
	@Query(value = "SELECT userId,userName, userEmail, userPhonenumber FROM student"
			+ " WHERE userId IN (SELECT userId FROM studentcoursemapping WHERE courseId=?1)", nativeQuery = true)
	public List<Object[]> findStudentByCourseId(@Param("courseId") int courseId);

	/**
	 * returns number of professor by emailId entered
	 * 
	 * @param userEmail
	 * @return professorId
	 */
	@Query(value = SQLConstants.SEARCH_PROFESSOR, nativeQuery = true)
	public int findProfessorByEmail(@Param("userEmail") String userEmail);

	/**
	 * returns professorId by emailId entered
	 * 
	 * @param userEmail
	 * @return professor
	 */
	@Query(value = SQLConstants.SELECT_PROFESSOR_BY_EMAIL, nativeQuery = true)
	public int getProfessorById(@Param("userEmail") String userEmail);

	/**
	 * returns list of object where each object contains details about courses
	 * 
	 * @param userId
	 * @return list of custom Objects
	 */
	@Query(value = "SELECT pcm.courseid, cc.courseName FROM professorcoursemapping pcm INNER JOIN coursecatalog cc ON pcm.courseid=cc.courseId WHERE pcm.userId=?1 AND pcm.isApproved=1", nativeQuery = true)
	public List<Object[]> listOfApprovedCourses(@Param("professorId") int userId);

}
