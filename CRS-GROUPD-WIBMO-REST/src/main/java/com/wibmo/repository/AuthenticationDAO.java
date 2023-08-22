/**
 * 
 */
package com.wibmo.repository;

import org.springframework.stereotype.Repository;

/**
 * To perform read and write operations in database for authentication.
 */
@Repository
public interface AuthenticationDAO {
	
	/**
	 * To if if credentials used for logging in are correct using SQL queries.
	 * @param userid
	 * @param password
	 * @param role
	 * @return True if credentials are correct else returns false.
	 */
	public boolean loggedin(String userEmail,String password,int role );

	/**
	 * To if if credentials used for changing password are correct using SQL queries.
	 * @param userId
	 * @param password
	 * @param role
	 */
	public void updatePassword(String userEmail, String password,int role);

}
