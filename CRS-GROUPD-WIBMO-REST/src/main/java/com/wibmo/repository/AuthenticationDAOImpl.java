/**
 * 
 */
package com.wibmo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wibmo.constant.SQLConstants;
import com.wibmo.repository.AuthenticationDAOImpl;
//import com.wibmo.dao.Logger;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class AuthenticationDAOImpl implements AuthenticationDAO {
	//plug logger in AuthenticationDAOImpl 
	
		@Autowired
		private Logger logger;
		//Plug logger in AuthenticationDAOImpl
		
		Connection conn = DButils.getConnection();

		@Override
		public boolean loggedin(String userEmail, String password,int role) {
			
			
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
								return true;
							}
							else {
								logger.debug("\n'Pending Approval from Admin'");
								return false;
							}
								
						}
						else
							return true;
							
					}
					else 
						logger.debug("\nInvalid Credentials\n");
					
					return false;
				}
		        
			}catch(SQLException se){
			      //Handle errors for JDBC
			      logger.error("SQL Exception: "+se.getMessage());
			   }catch(Exception e){
			      //Handle errors for Class.forName
				   logger.error("Unknown Exception");
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
