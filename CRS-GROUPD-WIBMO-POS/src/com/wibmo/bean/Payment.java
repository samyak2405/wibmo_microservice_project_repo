/**
 * 
 */
package com.wibmo.bean;

/**
 * Class representing the payment
 */
public class Payment {
	
	private long userId;
	private long transactionId;
	private int amount=100000;
	private int paymentStatus;
	
	
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
