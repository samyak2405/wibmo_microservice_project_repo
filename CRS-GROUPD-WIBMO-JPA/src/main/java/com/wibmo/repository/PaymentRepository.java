/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface PaymentRepository extends CrudRepository<User,Integer>{

}