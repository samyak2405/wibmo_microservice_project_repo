/**
 * 
 */
package com.wibmo.bean;

/**
 * 
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
	 * @param Set the Notification ID
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
	 * @param Set Notification Message
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
		
}
