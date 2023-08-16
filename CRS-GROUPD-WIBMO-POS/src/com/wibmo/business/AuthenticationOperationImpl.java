/**
 * 
 */
package com.wibmo.business;
import com.wibmo.dao.*;
/**
 * 
 */
public class AuthenticationOperationImpl implements AuthenticationOperation{
	AuthenticationDAO authenticate= AuthenticationDAOImpl.getInstance();
	
	@Override
	public boolean loggedin(int userid, String password) {
		// TODO Auto-generated method stub
		
		boolean verified=authenticate.loggedin(userid, password);
		
		return verified;
	}

}
