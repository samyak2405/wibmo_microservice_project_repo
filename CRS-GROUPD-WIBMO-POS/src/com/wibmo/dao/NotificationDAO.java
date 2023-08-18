/**
 * 
 */
package com.wibmo.dao;

import java.util.List;

import com.wibmo.bean.Notification;

/**
 * 
 */
public interface NotificationDAO {
	
	
	
	public List<Notification> getNotificationMessage(long studentId);
	
	public void sendNotificationMessage(int notifid,long studentId);
	
	
	
}
