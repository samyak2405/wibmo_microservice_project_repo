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

import com.wibmo.bean.Admin;
import com.wibmo.business.AdminOperation;
import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class AdminDAOImpl implements AdminDAO {

	public static volatile AdminDAOImpl instance = null;
	
	public AdminDAOImpl() {
		
	}
	
	public static AdminDAOImpl getInstance() {
		if(instance==null)
		{
			synchronized(AdminDAOImpl.class) {
				instance = new AdminDAOImpl();
			}
		}
		return instance;
	}
	
	Connection conn = DButils.getConnection();
	@Override
	public void addCourse(long courseId) {
		// TODO Auto-generated method stub
		
	}
	
	public void setGradeCard(int studentId,int courseId) {
		PreparedStatement stmt = null;
		
		try {	
			stmt = conn.prepareStatement(SQLConstants.INSERT_GRADECARD);
			stmt.setInt(1,studentId);
			stmt.setInt(2, courseId);
			stmt.setString(3, "NA");
			stmt.executeUpdate();
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}

	@Override
	public void dropCourse(long courseId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.DROP_COURSE);
			
			stmt.setLong(1, courseId);
			
			stmt.executeUpdate();
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		
	}

	@Override
	public boolean approveStudent(long studentId, List<Long> courses) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.INSERT_ADMIN);
			
			stmt.setLong(1, admin.getUserId());
			stmt.setString(2,admin.getUserName());
			stmt.setString(3,admin.getUserEmail());
			stmt.setString(4,admin.getUserPassword());
			stmt.setLong(5,admin.getUserPhonenumber());
			
			
			stmt.executeUpdate();
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}

	@Override
	public void assignCoursesProf(int professorId, int courseId) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Integer> getProfCourseData(int courseId)
	{
		PreparedStatement stmt = null;
		List<Integer> courseProfData= new ArrayList<>();
 		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.PROFESSOR_COURSE_DATA);
			
			stmt.setLong(1, courseId);
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
				courseProfData.add(rs.getInt("professorid"));
			}
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return courseProfData;
	}
	
	public List<Integer> getListOfCourses()
	{
		PreparedStatement stmt = null;
		List<Integer> listOfCourses= new ArrayList<>();
 		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.LIST_COURSES);
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
				listOfCourses.add(rs.getInt("courseId"));
			}
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return listOfCourses;
	}
	public void setProfCourse(int professorid, int courseid)
	{
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.PROF_COURSE_ASSIGNMENT);
			
			stmt.setInt(1,professorid);
			stmt.setInt(2,courseid);
			stmt.executeUpdate();

			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}

	@Override
	public boolean searchAdmin(long userId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(SQLConstants.SEARCH_ADMIN);
            stmt.setLong(1,userId);
            ResultSet rs=stmt.executeQuery();
            
            if(rs.next()) {
                if(rs.getInt("adminId")==userId) {

                    return true;}

            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

		return false;
	}
	public void setApprovedStudents() {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.APPROVE_STUDENT);
			stmt.executeUpdate();

			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}
	
	public List<Integer> pendingRegistration() {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		List<Integer> result = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.PENDING_REGISTRATION);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result.add(rs.getInt("studentid"));
			}
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return result;
	}

	@Override
	public void setApprovedStudentById(int studentId) {
		// TODO Auto-generated method stub
PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.APPROVE_STUDENT_BY_ID);
			stmt.setInt(1, studentId);
			stmt.executeUpdate();

			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		
	}

	@Override
	public int getAdminById(String userEmail) {
		// TODO Auto-generated method stub
PreparedStatement stmt = null;
		int adminId = 0;
		try {
			
			stmt = conn.prepareStatement(SQLConstants.SELECT_ADMIN_BY_EMAIL);
			stmt.setString(1, userEmail);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				adminId = rs.getInt("adminId");
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return adminId;
	}

	@Override
	public List<Integer> getProfessorsIds() {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		List<Integer> professors = new ArrayList<>();
		try {
			
			stmt = conn.prepareStatement(SQLConstants.SELECT_PROFESSORS_BY_ID);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				professors.add(rs.getInt("professorid"));
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return professors;
	}

	@Override
	public List<Integer> getProfessorCourses(int professorId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		List<Integer> courses = new ArrayList<>();
		try {
			
			stmt = conn.prepareStatement(SQLConstants.SELECT_PROFESSOR_COURSES);
			stmt.setInt(1, professorId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
				courses.add(rs.getInt("courseid"));
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return courses;
		
	}

	@Override
	public void approveCourse(int professor, int course) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(SQLConstants.APPROVE_PROFESSOR_COURSE);
			stmt.setInt(1, professor);
			stmt.setInt(2,course);
			stmt.executeUpdate();
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}
}
