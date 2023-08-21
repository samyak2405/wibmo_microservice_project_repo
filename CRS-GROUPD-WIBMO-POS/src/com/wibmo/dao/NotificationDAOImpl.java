/**
 * 
 */
package com.wibmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.Notification;
import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class NotificationDAOImpl implements NotificationDAO {

	public static volatile NotificationDAOImpl instance = null;
	
	private NotificationDAOImpl() {
		
	}
	
	public static NotificationDAOImpl getInstance() {
		if(instance==null)
		{
			synchronized(StudentDAOImpl.class) {
				instance = new NotificationDAOImpl();
			}
		}
		return instance;
	}
	
	Connection conn = DButils.getConnection();
	
	@Override
	public List<Notification> getNotificationMessage(long studentId) 
	{
		PreparedStatement stmt = null;
		
		List<Notification> notifs = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.GET_NOTIFICATION);
			stmt.setLong(1, studentId);
			
			ResultSet rs = stmt.executeQuery();
			
			
				while(rs.next()) {
					Notification notification =new Notification();
					notification.setId(rs.getInt("notificationId"));
					notification.setNotificationMessage(rs.getString("notificationMessage"));
					notifs.add(notification);
					}
			
			
				//notifs=null;
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		return notifs;
}

	
	@Override
	public void sendNotificationMessage(int notifId,long studentId) 
	{
		PreparedStatement stmt=null;
		try {
			stmt = conn.prepareStatement(SQLConstants.SEND_NOTIFICATION);
			stmt.setLong(1, notifId);
			stmt.setLong(2,studentId);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
