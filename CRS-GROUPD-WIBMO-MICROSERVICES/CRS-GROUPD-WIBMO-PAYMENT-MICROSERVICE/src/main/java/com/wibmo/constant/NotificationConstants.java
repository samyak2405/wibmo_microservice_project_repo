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
	
	public static final int FEE_PAYMENT_NOTIFICATION = 3;
	public static final int PAYMENT_SUCCESS_NOTIFICATION = 5;
	public static final int PAYMENT_REJECTED_NOTIFICATION = 6;

}
