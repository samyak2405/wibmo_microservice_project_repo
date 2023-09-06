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
import com.wibmo.entity.User;

/**
 * Repository related to Admin CRUD operations
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

	public User findByUserEmail(String userEmail);

	
}
