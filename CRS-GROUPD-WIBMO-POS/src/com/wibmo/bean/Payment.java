/**
 * 
 */
package com.wibmo.bean;

/**
 * 
 */
public class Payment {
	
	private long userId;
	private long transactionId;
	private long amount;
	
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
	public long getAmount() {
		return amount;
	}
	
	/**
	 * @param set amount
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}
}
