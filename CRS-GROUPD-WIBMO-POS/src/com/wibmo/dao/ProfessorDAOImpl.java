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
import com.wibmo.bean.Student;
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
	public void setGrades(long studentId, long courseId, String grade) {
		// TODO Auto-generated method stub
		PreparedStatement stmt=null;
		try {
			stmt = conn.prepareStatement(SQLConstants.SET_GRADES);
			stmt.setString(3,grade);
			stmt.setLong(1, studentId);
			stmt.setLong(2,courseId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean requestCourseOffering(int professorid,List<Long> courseIdList) {

        // TODO Auto-generated method stub

        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(SQLConstants.REQUEST_COURSE);

            for(Long courseId:courseIdList)
            {
                stmt.setLong(1,professorid);
                stmt.setLong(2,courseId);
                stmt.executeUpdate();

            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

         return false;

    }

 

	@Override
	public List<Student> viewStudentList(long courseId) {

        // TODO Auto-generated method stub

        PreparedStatement stmt = null;

        List<Student> students = new ArrayList<>();

        try {

            stmt = conn.prepareStatement(SQLConstants.STUDENT_LIST);

            stmt.setLong(1,courseId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
              Student student=new Student();

                student.setUserId(rs.getInt("studentid"));

                student.setUserName(rs.getString("studentname"));

                student.setUserEmail(rs.getString("studentemail"));

                student.setUserPhonenumber(rs.getLong("phonenumber"));

                students.add(student);
            }
     } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;

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
