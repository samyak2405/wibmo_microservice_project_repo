/**
 * 
 */
package com.wibmo.entity;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * Bean class for User entity
 */
@Entity
@Table(name="user")
@Inheritance

public class User implements Serializable {
	@Id
	@Column
	private int userId;
	@Column
	private String userName;
	@Column
	private String userEmail;
	@Column
	private long userPhonenumber;
	@Column
	private String userPassword;
	
	
	/*
	 * @return userId
	 * */
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId; 
	}
	
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userPhonenumber
	 */
	public long getUserPhonenumber() {
		return userPhonenumber;
	}
	/**
	 * @param userPhonenumber the userPhonenumber to set
	 */
	public void setUserPhonenumber(long userPhonenumber) {
		this.userPhonenumber = userPhonenumber;
	}
	
}
