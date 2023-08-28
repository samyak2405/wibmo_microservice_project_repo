/**
 * 
 */
package com.wibmo.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.wibmo.entity.Student;
import com.wibmo.entity.*;
import com.wibmo.repository.*;
/**
 * 
 */
@Service
public class AuthenticationOperationImpl implements AuthenticationOperation{
	@Autowired
	AuthenticationRepository authenticate;
	
	@Override
	public boolean loggedin(String userEmail, String userPassword,int role,StringBuilder msg) {
		// TODO Auto-generated method stub
		
		User user = null;
		if(role==1) {
			
		user=authenticate.studentLoggedin(userEmail);
		
		}
		else if(role==2)
		{
			user=authenticate.professorLoggedin(userEmail);
			
		}
		
		else if(role==3)
		{
			user=authenticate.adminLoggedin(userEmail);
		}
		if(user==null)
		{
			msg.append("User Not Found");
			return false;
		}
		if( userPassword.equalsIgnoreCase(user.getUserPassword()))
		{
			
			if(role==1) {
				
				if(((Student)user).getIsapproved()==1) {
					msg.append("Login Successful");
					return true;
				}
				else {
					msg.append("Registration Not Approved");
					return false;
				}
					
			}
			else
			{
				
				msg.append("Login Successful");
				return true;
			}
				
		}
		else 
		{
		msg.append("Invalid Credentials");
		return false;
		}
		
	}

	@Override
	public void updatePassword(String userEmail, String userPassword,int role) {
		// TODO Auto-generated method stub
		
		if(role==1) {
		authenticate.updateStudentPassword(userEmail,userPassword);
		}
		else if(role==2) {
			authenticate.updateProfessorPassword(userEmail,userPassword);
		}
		else if(role==3) {
			authenticate.updateAdminPassword(userEmail,userPassword);
		}
	}

}
