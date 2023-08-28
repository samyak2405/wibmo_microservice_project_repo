/**
 * 
 */
package com.wibmo.service;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.entity.Notification;
import com.wibmo.repository.*;

/**
 * 
 */

@Service
public class NotificationOperationImpl implements NotificationOperation {

	private Logger log=LogManager.getLogger();
	@Autowired
	public NotificationRepository notification;
	
	@Override
	public List<Notification> getNotificationMessage(long studentId) 
	{
		List<Notification>notifications=notification.getNotificationMessage(studentId);
		return notifications;
	}
	
	public void sendNotification(int notifid, long studentId)
	{
		notification.sendNotificationMessage(notifid, studentId);
	}
}
