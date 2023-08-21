/**
 * 
 */
package com.wibmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class CourseDAOImpl implements CourseDAO{

	public static volatile CourseDAOImpl instance = null;
	
	private CourseDAOImpl() {
		
	}
	
	public static CourseDAOImpl getInstance() {
		if(instance==null)
		{
			synchronized(StudentDAOImpl.class) {
				instance = new CourseDAOImpl();
			}
		}
		return instance;
	}
	
	Connection conn = DButils.getConnection();

    @Override
    public boolean searchCourse(long courseId) {

        // TODO Auto-generated method stub

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQLConstants.SEARCH_COURSE);
            stmt.setLong(1,courseId);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()) {
                if(rs.getInt("courseId")==courseId) {
                  return true;}
            }
        } catch (SQLException e) {

           e.printStackTrace(); 

        }

        return false;

    }

}
