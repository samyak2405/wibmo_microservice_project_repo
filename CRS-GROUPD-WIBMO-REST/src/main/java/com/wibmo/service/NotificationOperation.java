/**
 * 
 */
package com.wibmo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wibmo.model.Notification;

/**
 * For sending and receiving notifications
 */
@Service
public interface NotificationOperation {
	/**
	 * To get notifications of a specific user
	 * @param studentId
	 * @return list of notifications
	 */
	public List<Notification> getNotificationMessage(long studentId);
	
	/**
	 * To send a notification message to a user
	 * @param notifid
	 * @param studentId
	 */
	public void sendNotification(int notifid, long studentId);
}
