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
	
	@Autowired
	StudentRepository studentRepo;
	
	
	@Override
	public boolean loggedin(String userEmail, String password,int role,StringBuilder msg) {
		// TODO Auto-generated method stub
		if(role==1)
		{
			studentRepo.findId(userEmail)
		}
		
		boolean verified=authenticate.loggedin(userEmail, password, role,msg);
		return verified;
	}

	@Override
	public void updatePassword(String userEmail, String password,int role) {
		// TODO Auto-generated method stub
		authenticate.updatePassword(userEmail,password,role);
	}

}
