package com.wibmo.repository;

import com.wibmo.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDAO {
	
	/**
	 * To record payment details in the database.
	 * @param payment
	 */
	public void setPaymentRecord(Payment payment);
}
