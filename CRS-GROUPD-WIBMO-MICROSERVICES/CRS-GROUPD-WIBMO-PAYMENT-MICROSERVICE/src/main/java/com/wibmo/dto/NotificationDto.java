/**
 * 
 */
package com.wibmo.dto;

/**
 * Notification data transfer object
 */
public class NotificationDto {
	int userId;
	String notificationMessage;
	
	public NotificationDto(int userId, String  message) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.notificationMessage=message;
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
