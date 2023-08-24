package com.wibmo.repository;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wibmo.model.CourseCatalog;
import com.wibmo.model.Professor;
import com.wibmo.model.Student;
import com.wibmo.constant.SQLConstants;
import com.wibmo.repository.ProfessorDAO;
import com.wibmo.repository.ProfessorDAOImpl;
import com.wibmo.repository.StudentDAOImpl;
import com.wibmo.utils.DButils;

@Repository
public class ProfessorDAOImpl implements ProfessorDAO {
	
	
	
	Connection conn = DButils.getConnection();
//	@Override
//	public void registerCourses() {
//		// TODO Auto-generated method stub
//		
//	}
	


	@Override
	public void setGrades(long studentId, Integer courseId, String grade) {
		// TODO Auto-generated method stub
		
		PreparedStatement stmt=null;
		try {
			stmt = conn.prepareStatement(SQLConstants.SET_GRADES);
			stmt.setString(1,grade);
			stmt.setLong(2, studentId);
			stmt.setInt(3,courseId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean requestCourseOffering(int professorid,List<Integer> courseIdList) {

        // TODO Auto-generated method stub

        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(SQLConstants.REQUEST_COURSE);

            for(Integer courseId:courseIdList)
            {
                stmt.setLong(1,professorid);
                stmt.setLong(2,courseId);
                stmt.executeUpdate();

            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
        System.out.println();
         System.out.println("'Requested Course List is sent for approval'");
         return false;

    }

 

	@Override
	public List<Student> viewStudentList(Integer courseId) {

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

	@Override
	public void registerProfessor(Professor professor) {
PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.INSERT_PROFESSOR);
			
			stmt.setLong(1, professor.getUserId());
			stmt.setString(2,professor.getUserName());
			stmt.setString(3,professor.getUserEmail());
			stmt.setString(4,professor.getUserPassword());
			stmt.setLong(5,professor.getUserPhonenumber());
			
			
			stmt.executeUpdate();
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean searchProfessor(String userEmail)
	{

        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(SQLConstants.SEARCH_PROFESSOR);

            stmt.setString(1,userEmail);
            ResultSet rs=stmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public int getProfessorById(String userEmail) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int professorId = 0;
        try {

            stmt = conn.prepareStatement(SQLConstants.SELECT_PROFESSOR_BY_EMAIL);

            stmt.setString(1,userEmail);

            

            ResultSet rs=stmt.executeQuery();

            if(rs.next()) {
            	professorId = rs.getInt("professorid");
            }
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return professorId;
	}

	@Override
	public Map<Integer, String> listOfApprovedCourses(int userId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		Map<Integer,String> map = new HashMap<>();
		
        try {

            stmt = conn.prepareStatement(SQLConstants.LIST_APPROVED_COURSES);

            stmt.setInt(1,userId);

            ResultSet rs=stmt.executeQuery();

            while(rs.next()) {
            	map.put(rs.getInt("courseid"), rs.getString("courseName"));
            }
        } catch (SQLException e) {

            e.printStackTrace();

        }
		return map;
	}
}

