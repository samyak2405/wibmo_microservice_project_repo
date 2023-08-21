/**
 * 
 */
package com.wibmo.business;

/**
 * To authenticate different use cases
 */
public interface AuthenticationOperation {
	
	/**
	 * To authenticate the user for logging in
	 * @param userid
	 * @param password
	 * @param role
	 * @return True if credentials are true else returns false.
	 */
	public boolean loggedin(int userid ,String password ,int role);
	
	/**
	 * To authenticate the user for updating the password
	 * @param userId
	 * @param password
	 * @param role
	 */
	public void updatePassword(int userId,String password,int role);

}
