/**
 * 
 */
package com.wibmo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.model.Notification;
import com.wibmo.repository.NotificationDAO;

/**
 * 
 */

@Service
public class NotificationOperationImpl implements NotificationOperation {

	private Logger log=LogManager.getLogger();
	@Autowired
	public NotificationDAO notification;
	
	@Override
	public List<Notification> getNotificationMessage(long studentId) 
	{
		List<Notification>notifications=notification.getNotificationMessage(studentId);
		return notifications;
//		if(notifications!=null)
//			notifications.forEach(notif->log.info(notif.getNotificationMessage()));
//		else
//			log.info("No Notifications");
	}
	
	public void sendNotification(int notifid, long studentId)
	{
		notification.sendNotificationMessage(notifid, studentId);
	}
}
