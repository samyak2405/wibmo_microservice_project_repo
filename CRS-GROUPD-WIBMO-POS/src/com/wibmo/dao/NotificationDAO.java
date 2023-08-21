/**
 * 
 */
package com.wibmo.dao;

import java.util.List;

import com.wibmo.bean.Notification;

/**
 * To perform read and write operations in the database for sending and receiving notifications.
 */
public interface NotificationDAO {
	
	
	/**
	 * To retrieve notification messages from the database using SQL queries.
	 * @param studentId
	 * @return list of notifications for a specific student.
	 */
	public List<Notification> getNotificationMessage(long studentId);
	
	/**
	 * To send notification messages from the database using SQL queries.
	 * @param notifid
	 * @param studentId
	 */
	public void sendNotificationMessage(int notifid,long studentId);
	
	
	
}
