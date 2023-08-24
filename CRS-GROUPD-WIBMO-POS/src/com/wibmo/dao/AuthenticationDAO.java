/**
 * 
 */
package com.wibmo.dao;

/**
 * To perform read and write operations in database for authentication.
 */
public interface AuthenticationDAO {
	/**
	 * To if if credentials used for logging in are correct using SQL queries.
	 * @param userEmail
	 * @param password
	 * @param role
	 * @return True if credentials are correct else returns false.
	 */
	public boolean loggedin(String userEmail,String password,int role );

	/**
	 * To if if credentials used for changing password are correct using SQL queries.
	 * @param userEmail
	 * @param password
	 * @param role
	 */
	public void updatePassword(String userEmail, String password,int role);
}
