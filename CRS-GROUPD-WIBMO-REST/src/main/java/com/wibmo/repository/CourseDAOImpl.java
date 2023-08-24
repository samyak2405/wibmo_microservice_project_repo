package com.wibmo.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.repository.CourseDAO;
import com.wibmo.repository.CourseDAOImpl;
import com.wibmo.repository.StudentDAOImpl;
import com.wibmo.utils.DButils;


@Repository
public class CourseDAOImpl implements CourseDAO{

	public static volatile CourseDAOImpl instance = null;
	
	
	
	Connection conn = DButils.getConnection();

    @Override
    public boolean searchCourse(int courseId) {

        // TODO Auto-generated method stub

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQLConstants.SEARCH_COURSE);
            stmt.setInt(1,courseId);
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
