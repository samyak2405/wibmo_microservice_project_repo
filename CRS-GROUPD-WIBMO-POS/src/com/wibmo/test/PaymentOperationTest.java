/**
 * 
 */
package com.wibmo.test;






import static org.junit.Assert.*;

import org.junit.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.bean.Payment;
import com.wibmo.business.PaymentOperation;
import com.wibmo.business.PaymentOperationImpl;

/**
 * 
 */
public class PaymentOperationTest {
     PaymentOperation paymentoperation=null;
     Payment payment=null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		paymentoperation=new PaymentOperationImpl();
		payment=new Payment();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testOffline() {
		payment.setUserId(1);
		payment.setTransactionId(101002);
		payment.setAmount(10000);
		payment.setPaymentStatus(1);
		boolean flag=paymentoperation.offline(payment.getUserId());
		
		assertEquals(true,flag);
		
	}
	@Test
	public void testUPI() {
		payment.setUserId(1);
		payment.setTransactionId(101002);
		payment.setAmount(10000);
		payment.setPaymentStatus(1);
		boolean flag=paymentoperation.UPI(payment.getUserId());
		
		assertEquals(true,flag);
		
	}
	@Test
	public void testCards() {
		payment.setUserId(1);
		payment.setTransactionId(101002);
		payment.setAmount(10000);
		payment.setPaymentStatus(1);
		boolean flag=paymentoperation.cards(payment.getUserId());
		
		assertEquals(false,flag);
		
	}
	@Test
	public void testWallet() {
		payment.setUserId(1);
		payment.setTransactionId(101002);
		payment.setAmount(10000);
		payment.setPaymentStatus(1);
		boolean flag=paymentoperation.wallet(payment.getUserId());
		
		assertEquals(false,flag);
		
	}
	
	@Test
	public void testPayementStatus() {
		payment.setUserId(1);
		payment.setTransactionId(101002);
		payment.setAmount(10000);
		payment.setPaymentStatus(1);
		boolean flag=paymentoperation.getPaymentStatus(payment.getUserId());
		
		assertEquals(false,flag);
		
	}
	
	
	

}
