/**
 * 
 */
package com.wibmo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.Payment;

/**
 * Payment Repository related to Payment CRUD operations
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, UUID> {

}
