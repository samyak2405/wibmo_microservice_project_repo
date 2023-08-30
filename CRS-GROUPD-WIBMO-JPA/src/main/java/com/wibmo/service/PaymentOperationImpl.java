package com.wibmo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wibmo.entity.Payment;
import com.wibmo.repository.*;

/**
 * Payment Service Implementation
 */
@Service
public class PaymentOperationImpl implements PaymentOperation {

	@Autowired
	private PaymentRepository paymentDao;

	Payment paymentBean;

	public PaymentOperationImpl() {
		paymentBean = new Payment();
	}

	/**
	 * To get the amount to be paid
	 * 
	 * @param studentId
	 * @return The amount to be paid or has already been paid.
	 */
	@Override
	public int getAmount(long studentId) {
		// TODO Auto-generated method stub
		return paymentBean.getAmount();
	}

	/**
	 * To get the payment status of a student
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean getPaymentStatus(long studentId) {

		if (paymentBean.getPaymentStatus() == 1)
			return true;
		else
			return false;
	}

	/**
	 * For paying the fee offline
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	@Transactional
	public boolean offline(long studentId) {

		paymentBean.setPaymentStatus(1);
		return true;
	}

	/**
	 * For paying the fees via UPI
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean UPI(long studentId) {

		this.paymentBean.setPaymentStatus(1);
		return true;
	}

	/**
	 * For paying the fees using debit/credit card
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean cards(long studentId) {

		this.paymentBean.setPaymentStatus(0);
		return false;
	}

	/**
	 * For paying the fees using online wallet.
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean wallet(long studentId) {
		this.paymentBean.setPaymentStatus(0);
		return false;
	}

	/**
	 * 
	 * @param studentId
	 * @param paymentStatus
	 * @return the Payment Bean object containing payment record of that particular
	 *         student.
	 */
	@Override
	@Transactional
	public void recordPayment(long studentId, boolean paymentStatus) {
		if (paymentStatus)
			this.paymentBean.setPaymentStatus(1);
		else
			this.paymentBean.setPaymentStatus(0);

		this.paymentBean.setUserId(studentId);
		this.paymentBean.setTransactionId(1000000 + studentId);

		this.paymentDao.save(paymentBean);
	}

}
