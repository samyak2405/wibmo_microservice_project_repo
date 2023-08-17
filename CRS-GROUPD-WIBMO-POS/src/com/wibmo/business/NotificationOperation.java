/**
 * 
 */
package com.wibmo.business;

/**
 * 
 */
public interface NotificationOperation {
	
	public void getNotificationMessage(long studentId);
	public void sendNotification(int notifid, int studentId);
}
