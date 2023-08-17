/**
 * 
 */
package com.wibmo.dao;

/**
 * 
 */
public interface paymentDAO {
	public void getAmount(long studentId);
	public void getPaymentStatus(long studentId);
	public boolean offline();
	public boolean UPI();
	public boolean cards();
	public boolean wallet();
}
