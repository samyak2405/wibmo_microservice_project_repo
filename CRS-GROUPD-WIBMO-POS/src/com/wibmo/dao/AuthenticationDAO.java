/**
 * 
 */
package com.wibmo.dao;

/**
 * 
 */
public interface AuthenticationDAO {
	public boolean loggedin(String userEmail,String password,int role );

	public void updatePassword(String userEmail, String password,int role);
}
