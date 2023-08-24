/**
 * 
 */
package com.wibmo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.repository.AuthenticationDAOImpl;
//import com.wibmo.dao.Logger;
import com.wibmo.utils.DButils;

/**
 * 
 */

@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO {
	
	
		
		
		Connection conn = DButils.getConnection();

		@Override
		public boolean loggedin(String userEmail, String password,int role,StringBuilder msg) {
			
			
			PreparedStatement stmt = null;
			String roleEmail=null;
			
			try {
				if(role==1)
				{
					stmt = conn.prepareStatement(SQLConstants.VERIFY_STUDENT);
					roleEmail="studentemail";
				}
				
				else if(role==2)
				{
					stmt = conn.prepareStatement(SQLConstants.VERIFY_PROFESSOR);
					roleEmail="professoremail";
				}
				
				else if(role==3)
				{
					stmt = conn.prepareStatement(SQLConstants.VERIFY_ADMIN);
					roleEmail="adminEmail";
				}
				
				stmt.setString(1, userEmail);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					if(userEmail.equals(rs.getString(roleEmail)) && password.equalsIgnoreCase(rs.getString("password")))
					{
						
						if(role==1) {
							if(rs.getInt("isapproved")==1) {
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
		        
			}catch(SQLException se){
			      //Handle errors for JDBC
			      
			   }catch(Exception e){
			      //Handle errors for Class.forName
				   
			   }
			
			return false;
		}

		@Override
		public void updatePassword(String userEmail, String password,int role) {
			// TODO Auto-generated method stub
			PreparedStatement stmt = null;
			
			try {
				if(role==1)
				{
					stmt = conn.prepareStatement(SQLConstants.UPDATE_PASSWORD_STUDENT);
					
				}
				
				else if(role==2)
				{
					stmt = conn.prepareStatement(SQLConstants.UPDATE_PASSWORD_PROFESSOR);
					
				}
				
				else if(role==3)
				{
					stmt = conn.prepareStatement(SQLConstants.UPDATE_PASSWORD_ADMIN);
	                   
				}
				
				
				stmt.setString(1, password);
				stmt.setString(2, userEmail);
				stmt.executeUpdate();
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
