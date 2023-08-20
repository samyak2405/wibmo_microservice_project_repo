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
	public boolean loggedin(String userEmail, String password,int role) {
		// TODO Auto-generated method stub
		
		boolean verified=authenticate.loggedin(userEmail, password, role);
		
		return verified;
	}

	@Override
	public void updatePassword(String userEmail, String password,int role) {
		// TODO Auto-generated method stub
		authenticate.updatePassword(userEmail,password,role);
	}

}
