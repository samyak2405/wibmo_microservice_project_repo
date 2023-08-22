/**
 * 
 */
package com.wibmo.model;

import java.io.Serializable;

/**
 * Class to represent Notifications
 */
public class Notification implements Serializable{
	
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
