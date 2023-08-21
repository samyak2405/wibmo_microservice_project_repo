/**
 * 
 */
package com.wibmo.business;

import java.util.List;

import org.apache.log4j.Logger;

import com.wibmo.bean.Notification;
import com.wibmo.dao.NotificationDAO;
import com.wibmo.dao.NotificationDAOImpl;

/**
 * 
 */
public class NotificationOperationImpl implements NotificationOperation {

	static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	NotificationDAO notification=NotificationDAOImpl.getInstance();
	
	@Override
	public void getNotificationMessage(long studentId) 
	{
		List<Notification>notifications=notification.getNotificationMessage(studentId);
		if(notifications!=null)
			notifications.forEach(notif->log.info(notif.getNotificationMessage()));
		else
			log.info("No Notifications");
	}
	
	public void sendNotification(int notifid, int studentId)
	{
		notification.sendNotificationMessage(notifid, studentId);
	}
}
