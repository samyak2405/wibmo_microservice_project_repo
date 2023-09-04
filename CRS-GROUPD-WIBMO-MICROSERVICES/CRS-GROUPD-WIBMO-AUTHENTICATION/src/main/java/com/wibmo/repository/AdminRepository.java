/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Admin;

/**
 * Repository related to Admin CRUD operations
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

	

	/**
	 * returns the count of user where email id is matching
	 * 
	 * @param userEmail
	 * @return count
	 */
	@Query(value = SQLConstants.SEARCH_ADMIN, nativeQuery = true)
	public int findByEmail(@Param("userEmail") String userEmail);

	

	
}
