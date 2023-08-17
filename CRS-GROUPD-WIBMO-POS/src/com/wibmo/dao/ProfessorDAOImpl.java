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
import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class ProfessorDAOImpl implements ProfessorDAO {
	
	
	
	public static volatile ProfessorDAOImpl instance = null;
	
	private ProfessorDAOImpl() {
		
	}
	
	public static ProfessorDAOImpl getInstance() {
		if(instance==null)
		{
			synchronized(StudentDAOImpl.class) {
				instance = new ProfessorDAOImpl();
			}
		}
		return instance;
	}
	
	Connection conn = DButils.getConnection();
//	@Override
//	public void registerCourses() {
//		// TODO Auto-generated method stub
//		
//	}
	


	@Override
	public void setGrades(long studentId, long courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean requestCourseOffering(List<Long> courseIdList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewStudentList(long courseId) {
		// TODO Auto-generated method stub
		
	}

	public List<CourseCatalog> viewCourseCatalog() {
		PreparedStatement stmt = null;
		List<CourseCatalog> courses = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.COURSE_CATALOG);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				CourseCatalog course = new CourseCatalog();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				course.setProfessorName(rs.getString("professorName"));
				course.setPrerequisites(rs.getString("prerequisites"));
				courses.add(course);
				}
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		return courses;

}
}
