/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface AdminRepository extends CrudRepository<User,Integer> {
	@Modifying
	@Query("SELECT count(*) FROM admin WHERE adminId=?")
	public int searchAdmin(@Param("adminId") int adminId) ;
	
}
