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

import com.wibmo.entity.Student;
import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
	
	@Modifying
	@Transactional
	@Query(value=" UPDATE studentcoursemapping SET isRegister=1 WHERE userId=?1", nativeQuery = true)
	public void registerCourses(@Param("studentId")int studentId);
	


	@Query(value="SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE userId=?1", nativeQuery=true)
	public int getCourseCount(@Param("userId")int studentId);
	
	@Modifying
	@Query(value="DELETE FROM studentcoursemapping WHERE userId=? && courseId=?", nativeQuery =  true)
	public void dropCourses(@Param("userId")int studentId,@Param("courseId")int courseId);

	
	@Query(value="SELECT isRegister FROM studentcoursemapping WHERE userId=:studentId AND courseId=:courseId", nativeQuery = true )
	public Integer findCoursePreference(@Param("studentId")int studentId,@Param("courseId") int courseId);

	
	@Query(value="SELECT COUNT(*) FROM gradecard where userId=?1", nativeQuery = true)
	public int isApproved(@Param("userId")int studentId);

	@Query(value=SQLConstants.STUDENT_BY_EMAIL, nativeQuery =  true)
	public int findByEmail(@Param("userEmail")String userEmail);

	@Query(value="SELECT DISTINCT(userId) as uniqueStudent FROM studentcoursemapping", nativeQuery =  true)
	public List<Integer> getStudentIds();

	@Query(value="SELECT COUNT(*) FROM studentcoursemapping WHERE userId=:studentId AND isRegister=1", nativeQuery = true)
	public Integer isStudentRegistered(@Param("studentId")int studentId);

	@Query(value="SELECT COUNT(*) FROM gradecard where userId=?1", nativeQuery =  true)
	public int isCourseRegistrationApproved(@Param("studentId")int studentId);

	@Query(value="SELECT courseId, coursecategory FROM studentcoursemapping WHERE userId=?", nativeQuery =  true)
	public List<Object[]> getStudentCourseData(@Param("studentId")int studentId);

	@Query(value="SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE courseId=?", nativeQuery =  true)
	public int getStudentCourseCount(@Param("courseId")Integer courseId);

	@Query(value="SELECT gradecard.courseId, courseCatalog.courseName FROM "
			+ "gradecard as gradecard INNER JOIN coursecatalog as courseCatalog ON"
			+ " gradecard.courseId=courseCatalog.courseId WHERE gradecard.userId=?1", nativeQuery = true)
	public List<Object[]> listCourse(@Param("studentId")int studentId);

	@Query(value=SQLConstants.SELECT_ADDED_COURSE, nativeQuery =  true)
	public List<Object[]> getAddedCourses(@Param("userId")int userId);

	
	@Query(value="SELECT courseId, grade FROM gradecard where userId=?1", nativeQuery =  true)
	public List<Object[]> viewReportCard(@Param("studentId")int studentId);

	@Modifying
	@Query(value=SQLConstants.APPROVE_STUDENT,nativeQuery =  true)
	public void setApprovedStudentById(int id);
	
	
}
