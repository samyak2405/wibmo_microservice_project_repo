/**
 * 
 */
package com.wibmo.dto;

import java.io.Serializable;

/**
 * Notification data transfer object
 */
public class NotificationDto implements Serializable{
	int userId;
	String notificationMessage;
	
	
	
	
	public NotificationDto(int userId, String notificationMessage) {
		super();
		this.userId = userId;
		this.notificationMessage = notificationMessage;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the notificationMessage
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}
	/**
	 * @param notificationMessage the notificationMessage to set
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	
	
	
}
