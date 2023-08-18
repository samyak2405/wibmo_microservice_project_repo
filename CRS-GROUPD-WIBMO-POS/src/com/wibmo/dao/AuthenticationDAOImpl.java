/**
 * 
 */
package com.wibmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class AuthenticationDAOImpl implements AuthenticationDAO {
	//plug logger in AuthenticationDAOImpl 
	private static Logger logger = Logger.getLogger(AuthenticationDAOImpl.class);

	public static volatile AuthenticationDAOImpl instance = null;
	
	private AuthenticationDAOImpl() {
		
	}
	
	public static AuthenticationDAOImpl getInstance() {
		if(instance==null)
		{
			synchronized(AuthenticationDAOImpl.class) {
				instance = new AuthenticationDAOImpl();
			}
		}
		return instance;
	}
	
	Connection conn = DButils.getConnection();

	@Override
	public boolean loggedin(int userid, String password,int role) {
		
		
		PreparedStatement stmt = null;
		String roleId=null;
		
		try {
			if(role==1)
			{
				stmt = conn.prepareStatement(SQLConstants.VERIFY_STUDENT);
				roleId="studentid";
			}
			
			else if(role==2)
			{
				stmt = conn.prepareStatement(SQLConstants.VERIFY_PROFESSOR);
				roleId="professorid";
			}
			
			else if(role==3)
			{
				stmt = conn.prepareStatement(SQLConstants.VERIFY_ADMIN);
				roleId="adminId";
			}
			
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(userid==rs.getInt(roleId) && password.equalsIgnoreCase(rs.getString("password")))
				{
					
					if(rs.getInt("isapproved")==1)
						return true;
					else
					{
						logger.debug("Pending Approval from Admin");
						return false;
					}
						
				}
				else
					logger.debug("Invalid Credentials");
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
	public void updatePassword(int userId, String password,int role) {
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
			stmt.setLong(2, userId);
			stmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
