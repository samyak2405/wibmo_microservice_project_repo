/**
 * 
 */
package com.wibmo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.wibmo.entity.Payment;
import com.wibmo.repository.*;

/**
 * 
 */

@Service
public class PaymentOperationImpl implements PaymentOperation{

	Payment paymentBean;
	
	@Autowired
	PaymentRepository paymentDao;
	
	public PaymentOperationImpl() {
		paymentBean = new Payment();
	}
	
	@Override
	public int getAmount(long studentId) {
		// TODO Auto-generated method stub
		return paymentBean.getAmount();
	}

	@Override
	public boolean getPaymentStatus(long studentId) {
		
		if(paymentBean.getPaymentStatus()==1)
			return true;
		else
			return false;
	}


	@Override
	public boolean offline(long studentId) {
		
		paymentBean.setPaymentStatus(1);
		return true;
	}

	@Override
	public boolean UPI(long studentId) {
		
		this.paymentBean.setPaymentStatus(1);
		return true;
	}

	@Override
	public boolean cards(long studentId) {

		this.paymentBean.setPaymentStatus(0);
		return false;
	}
 
	@Override
	public boolean wallet(long studentId) {
		this.paymentBean.setPaymentStatus(0);
		return false;
	}
	
	public void recordPayment(long studentId,boolean paymentStatus)
	{
		if(paymentStatus)
			this.paymentBean.setPaymentStatus(1);
		else
			this.paymentBean.setPaymentStatus(0);
		
		this.paymentBean.setUserId(studentId);
		this.paymentBean.setTransactionId(1000000+studentId);
		this.paymentDao.save(paymentBean);
	}
	

}
