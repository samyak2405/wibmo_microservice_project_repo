/**
 * 
 */
package com.wibmo.dto;

import java.io.Serializable;

/**
 * 
 */
public class UpdatePasswordDto implements Serializable {
	private String userEmail;
	private String userPassword;
	private String userPasswordNew;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPasswordNew() {
		return userPasswordNew;
	}
	public void setUserPasswordNew(String userPasswordNew) {
		this.userPasswordNew = userPasswordNew;
	}
	
	
}
