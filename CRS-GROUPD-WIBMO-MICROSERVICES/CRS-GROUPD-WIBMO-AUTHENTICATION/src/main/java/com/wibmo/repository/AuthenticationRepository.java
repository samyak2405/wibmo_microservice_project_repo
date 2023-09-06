/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.*;

/**
 * Repository related to Authentication CRUD operations
 */
@Repository
public interface AuthenticationRepository extends CrudRepository<User, Integer> {
	/**
	 * returns the student object related to userEmail entered
	 * 
	 * @param userEmail
	 * @return Student
	 */
	@Query(value = SQLConstants.VERIFY_STUDENT, nativeQuery = true)
	public Student studentLoggedin(@Param("userEmail") String userEmail);

	/**
	 * returns the professor object related to userEmail entered
	 * 
	 * @param userEmail
	 * @return Professor
	 */
	@Query(value = SQLConstants.VERIFY_PROFESSOR, nativeQuery = true)
	public Professor professorLoggedin(@Param("userEmail") String userEmail);

	/**
	 * returns the Admin object related to userEmail entered
	 * 
	 * @param userEmail
	 * @return Admin
	 */
	@Query(value = SQLConstants.VERIFY_ADMIN, nativeQuery = true)
	public Admin adminLoggedin(@Param("userEmail") String userEmail);

	/**
	 * updates the userpassword in student according to userEmail entered
	 * 
	 * @param userEmail
	 * @param userPassword
	 */
	@Modifying
	@Query(value =SQLConstants.UPDATE_PASSWORD_STUDENT, nativeQuery = true)
	public void updateStudentPassword(@Param("userEmail") String userEmail, String userPassword);

	/**
	 * updates the userpassword in professor according to userEmail entered
	 * 
	 * @param userEmail
	 * @param userPassword
	 */
	@Modifying
	@Query(value = SQLConstants.UPDATE_PASSWORD_PROFESSOR, nativeQuery = true)
	public void updateProfessorPassword(@Param("userEmail") String userEmail, String userPassword);

	/**
	 * updates the userpassword in Admin according to userEmail entered
	 * 
	 * @param userEmail
	 * @param userPassword
	 */
	@Modifying
	@Query(value = SQLConstants.UPDATE_PASSWORD_ADMIN, nativeQuery = true)
	public void updateAdminPassword(@Param("userEmail") String userEmail, String userPassword);

}
