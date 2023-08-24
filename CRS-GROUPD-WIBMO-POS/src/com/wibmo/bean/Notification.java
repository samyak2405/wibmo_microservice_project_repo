/**
 * 
 */
package com.wibmo.bean;

/**
 * Class to represent Notifications
 */
public class Notification {
	
	private int id;
	
	private String notificationMessage;

	/**
	 *@return Notification ID 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return Notification Message
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}
	
	/**
	 * 
	 * @param notificationMessage
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
		
}
