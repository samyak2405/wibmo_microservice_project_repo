/**
 * 
 */
package com.wibmo.bean;

/**
 * 
 */
public class Notification {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNotificationMessage() {
		return notificationMessage;
	}
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	private int id;
	private String notificationMessage;	
}
