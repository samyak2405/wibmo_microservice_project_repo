/**
 * 
 */
package com.wibmo.business;

/**
 * 
 */
public interface AuthenticationOperation {
	public boolean loggedin(int userid ,String password ,int role);
	public void updatePassword(int userId,String password,int role);

}
