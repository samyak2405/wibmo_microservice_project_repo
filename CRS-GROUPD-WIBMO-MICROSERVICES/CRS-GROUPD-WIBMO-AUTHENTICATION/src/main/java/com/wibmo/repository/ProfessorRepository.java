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
	 * returns number of professor by emailId entered
	 * 
	 * @param userEmail
	 * @return professorId
	 */
	@Query(value = SQLConstants.SEARCH_PROFESSOR, nativeQuery = true)
	public int findProfessorByEmail(@Param("userEmail") String userEmail);

	

}
