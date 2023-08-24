/**
 * 
 */
package com.wibmo.service;

import org.springframework.stereotype.Service;

/**
 * For sending and receiving notifications
 */
@Service
public interface NotificationOperation {
	/**
	 * To get notifications of a specific user
	 * @param studentId
	 */
	public void getNotificationMessage(long studentId);
	
	/**
	 * To send a notification message to a user
	 * @param notifid
	 * @param studentId
	 */
	public void sendNotification(int notifid, long studentId);
}
