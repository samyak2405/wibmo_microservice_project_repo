/**
 * 
 */
package com.wibmo.constant;

import org.springframework.stereotype.Component;

/**
 * Constants for for storing notification ids of the notification messages in
 * the database.
 */
@Component
public class NotificationConstants {

	/**
	 * Student Registration Approved
	 */
	public static final int APPROVE_REGISTRATION_NOTIFICATION = 1;
	
	/**
	 * Student Registration Rejected
	 */
	public static final int REJECT_REGISTRATION_NOTIFICATION = 2;
	
	/**
	 * Pay registration fee notification
	 */
	public static final int FEE_PAYMENT_NOTIFICATION = 3;
	
	/**
	 * Courses Assigned to student
	 */
	public static final int COURSE_ASSIGNED_NOTIFICATION = 4;
	
	/**
	 * Payment Success
	 */
	public static final int PAYMENT_SUCCESS_NOTIFICATION = 5;
	
	/**
	 * Payment Failure
	 */
	public static final int PAYMENT_REJECTED_NOTIFICATION = 6;

}
