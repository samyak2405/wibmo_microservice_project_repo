package com.wibmo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.bean.Payment;
import com.wibmo.business.PaymentOperation;
import com.wibmo.business.PaymentOperationImpl;
import com.wibmo.dao.paymentDAO;
import com.wibmo.dao.paymentDAOImpl;

public class paymentDAOTest {
	 paymentDAO paymentoperation=null;
     Payment payment=null;
	@Before
	public void setUp() throws Exception {
		paymentoperation= paymentDAOImpl.getInstance();
		payment=new Payment();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void testSetPaymentRecord() {
		payment.setUserId(1);
		payment.setTransactionId(101002);
		payment.setAmount(10000);
		payment.setPaymentStatus(1);
		
		
	}

}
