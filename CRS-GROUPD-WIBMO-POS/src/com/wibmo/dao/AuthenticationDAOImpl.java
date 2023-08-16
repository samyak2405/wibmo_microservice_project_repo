/**
 * 
 */
package com.wibmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class AuthenticationDAOImpl implements AuthenticationDAO {

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
	public boolean loggedin(int userid, String password) {
		
		
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.VERIFY_STUDENT);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				if(userid==rs.getInt("studentid"))
				{
					
					if(password.equalsIgnoreCase(rs.getString("password")))
					{
						return true;
					}
					System.out.println(rs.getString("password")+" "+ password);
					return false;
				}
				System.out.println("User Does Not Exist!");
				return false;
			}
	        
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		
		return false;
	}

}
