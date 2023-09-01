/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;

import com.wibmo.entity.Admin;
import com.wibmo.entity.SemesterRegistration;

/**
 * 
 */
public interface SemesterRegistrationRepository extends CrudRepository<SemesterRegistration, Integer>
{
	
}
