/**
 * 
 */
package com.wibmo.service;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wibmo.model.Notification;
import com.wibmo.repository.NotificationDAO;

/**
 * 
 */
public class NotificationOperationImpl implements NotificationOperation {

	@Autowired
	public Logger log;
	@Autowired
	public NotificationDAO notification;
	
	@Override
	public void getNotificationMessage(long studentId) 
	{
		List<Notification>notifications=notification.getNotificationMessage(studentId);
		if(notifications!=null)
			notifications.forEach(notif->log.info(notif.getNotificationMessage()));
		else
			log.info("No Notifications");
	}
	
	public void sendNotification(int notifid, long studentId)
	{
		notification.sendNotificationMessage(notifid, studentId);
	}
}
