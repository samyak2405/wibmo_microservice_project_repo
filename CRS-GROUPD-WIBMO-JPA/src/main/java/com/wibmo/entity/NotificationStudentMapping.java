/**
 * 
 */
package com.wibmo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="notificationstudentmapping")
public class NotificationStudentMapping {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="notificationId")
	private Notification notification;
	
	public NotificationStudentMapping() {}
	
	public NotificationStudentMapping(Student student, Notification notification) {
		this.student = student;
		this.notification = notification;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}


}
