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
		notification.getNotificationMessage(studentId).forEach(notif->System.out.println(notif.getNotificationMessage()));
	}
	
	public void sendNotification(int notifid, int studentId)
	{
		notification.sendNotificationMessage(notifid, studentId);
	}
}
