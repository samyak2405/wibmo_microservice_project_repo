/**
 * 
 */
package com.wibmo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.wibmo.entity.Notification;

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
	
	
	public List<Notification> getNotificationMessage(int studentId);

	public void sendNotification(int notificationId, int userId);
	
}
