/**
 * 
 */
package com.wibmo.business;

/**
 * For sending and receiving notifications
 */
public interface NotificationOperation {
	/**
	 * To get notifications of a specific user
	 * @param studentId
	 */
	public void getNotificationMessage(long studentId);
	
	/**
	 * To send a notification message to a user
	 * @param notifid
	 * @param studentId
	 */
	public void sendNotification(int notifid, long studentId);
}
