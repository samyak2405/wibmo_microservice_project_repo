/**
 * 
 */
package com.wibmo.dao;

import com.wibmo.bean.Payment;

/**
 * To perform read and write operations in the database for Payment.
 */
public interface paymentDAO {
	
	/**
	 * To record payment details in the database.
	 * @param payment
	 */
	public void setPaymentRecord(Payment payment);
}
