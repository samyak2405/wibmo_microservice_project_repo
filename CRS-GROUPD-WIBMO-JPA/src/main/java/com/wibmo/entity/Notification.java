/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Class to represent Notifications
 */
@Entity
@Table(name="notification")
public class Notification implements Serializable{
	
	@Id
	@Column(name="notificationId")
	private int id;
	@Column
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
