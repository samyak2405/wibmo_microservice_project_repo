/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class representing the payment
 */

@Entity
@Table(name = "payment")
public class Payment implements Serializable {
	@Id
	@Column(name = "studentId")
	private long userId;
	@Column
	private int paymentStatus;
	@Column
	private long transactionId;
	@Column
	private int amount;

	public Payment() {
		amount = 1000000;
	}

	/**
	 * @return User ID
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param Get User Id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return Transaction Id
	 */
	public long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param Get Transaction Id
	 */
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param set amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return
	 */
	public int getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * 
	 * @param paymentStatus
	 */
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
