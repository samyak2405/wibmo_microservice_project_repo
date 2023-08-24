/**
 * 
 */
package com.wibmo.model;

import java.io.Serializable;

/**
 * Bean class for User entity
 */

public class User implements Serializable {
	private int userId;
	private String userName;
	private String userEmail;
	private long userPhonenumber;
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
