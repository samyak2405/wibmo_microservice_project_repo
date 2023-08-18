/**
 * 
 */
package com.wibmo.business;

import java.util.List;

import com.wibmo.bean.Notification;
import com.wibmo.dao.NotificationDAO;
import com.wibmo.dao.NotificationDAOImpl;

/**
 * 
 */
public class NotificationOperationImpl implements NotificationOperation {

	NotificationDAO notification=NotificationDAOImpl.getInstance();
	
	@Override
	public void getNotificationMessage(long studentId) 
	{
		List<Notification>notifications=notification.getNotificationMessage(studentId);
		if(notifications!=null)
			notifications.forEach(notif->System.out.println(notif.getNotificationMessage()));
		else
			System.out.println("No Notifications");
	}
	
	public void sendNotification(int notifid, int studentId)
	{
		notification.sendNotificationMessage(notifid, studentId);
	}
}
