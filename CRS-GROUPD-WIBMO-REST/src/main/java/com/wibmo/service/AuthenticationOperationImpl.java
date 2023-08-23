/**
 * 
 */
package com.wibmo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.repository.*;
/**
 * 
 */
@Service
public class AuthenticationOperationImpl implements AuthenticationOperation{
	@Autowired
	AuthenticationDAO authenticate;
	
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
