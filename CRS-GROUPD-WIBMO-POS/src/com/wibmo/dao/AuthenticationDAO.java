/**
 * 
 */
package com.wibmo.dao;

/**
 * 
 */
public interface AuthenticationDAO {
	public boolean loggedin(int userid ,String password,int role );

	public void updatePassword(int userId, String password,int role);
}
