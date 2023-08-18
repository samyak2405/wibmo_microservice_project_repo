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
	Connection conn = DButils.getConnection();
	@Override
	public boolean searchCourse(int courseId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(SQLConstants.SEARCH_COURSE);
            stmt.setLong(1, courseId);
            ResultSet rs=stmt.executeQuery();

            if(rs.next()) {
                if(rs.getInt("courseId")==courseId) {

                    return true;}

            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

		return false;
	}

}
