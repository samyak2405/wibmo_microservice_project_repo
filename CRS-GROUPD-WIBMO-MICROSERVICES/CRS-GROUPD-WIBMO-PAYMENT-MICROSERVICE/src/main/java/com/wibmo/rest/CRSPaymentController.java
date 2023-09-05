/**
 * 
 */
package com.wibmo.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.service.PaymentOperation;
import com.wibmo.service.StudentOperation;


/**
 * 
 */
@RestController
@RequestMapping(value="/api/payment")
@CrossOrigin
public class CRSPaymentController {
	
	@Autowired
	private PaymentOperation payment;
	
	@Autowired
	private StudentOperation studentOp;
	
	/**
	 * payFee
	 * 
	 * @param userId
	 * @param paymentMethod
	 * @param onlineMethod
	 * @return message if payment is successful or not
	 */
	@RequestMapping(value = "/{id}/payfee/{paymentMethod}", method = RequestMethod.POST)
	public ResponseEntity<String> payFee(@PathVariable(value = "id") int userId,
			@PathVariable(value = "paymentMethod") String paymentMethod,
			@RequestParam(required = false) String onlineMethod) 
	{
		boolean status = false;
		String transactionId = null;
try {
		if (studentOp.isStudentRegistered(userId)>0) {

			if (paymentMethod.equals("offline")) {
				status = payment.offline(userId);
				transactionId=payment.recordPayment(userId,paymentMethod, status);
			}
			else if (paymentMethod.equals("online")) {
				if (onlineMethod.equals("UPI")) {
					status = payment.UPI(userId);
					transactionId=payment.recordPayment(userId,paymentMethod, status);
				} else if (onlineMethod.equals("cards")) {
					status = payment.cards(userId);
					transactionId=payment.recordPayment(userId,paymentMethod, status);
				} else if (onlineMethod.equals("Wallet")) {
					status = payment.wallet(userId);
					transactionId=payment.recordPayment(userId,paymentMethod, status);
				}

				transactionId=payment.recordPayment(userId,paymentMethod, status);
			}
			
			if (status == true) {
//				notificationOp.sendNotification(NotificationConstants.PAYMENT_SUCCESS_NOTIFICATION, userId);
				return new ResponseEntity<String>("Payment Successful with TransactionId:"+transactionId, HttpStatus.OK);
			}

			else {
//				notificationOp.sendNotification(NotificationConstants.PAYMENT_REJECTED_NOTIFICATION, userId);
				return new ResponseEntity<String>("Payment Failed", HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<String>("Student courses are not approved by admin", HttpStatus.NOT_ACCEPTABLE);
		}

	}catch(StudentAlreadyRegisteredException e)
	{
			return new ResponseEntity<String>("Student has already paid the fee!", HttpStatus.NOT_ACCEPTABLE);
	}
	}

}
