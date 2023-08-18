/**
 * 
 */
package com.wibmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wibmo.constant.SQLConstants;
import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.GradeCard;
import com.wibmo.bean.Student;
import com.wibmo.bean.StudentCourseMap;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class StudentDAOImpl implements StudentDAO {

//	public static StudentDAOImpl studentDao = new StudentDAOImpl();
	public static volatile StudentDAOImpl instance = null;
	
	private StudentDAOImpl() {
		
	}
	
	public static StudentDAOImpl getInstance() {
		if(instance==null)
		{
			synchronized(StudentDAOImpl.class) {
				instance = new StudentDAOImpl();
			}
		}
		return instance;
	}
	
	Connection conn = DButils.getConnection();
	

	
	public int getStudentCourseCount(int courseid) {
		PreparedStatement stmt = null;
		int count = 0;
		try {
			
			stmt = conn.prepareStatement(SQLConstants.COUNT_STUDENT_COURSES);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("courseCount");
			}
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return count;
	}
	
	
	
	
	public int getCourseCount(long studentId) {
		PreparedStatement stmt = null;
		int count = 0;
		try {
			
			stmt = conn.prepareStatement(SQLConstants.COUNT_COURSES);
			stmt.setLong(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("courseCount");
			}
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return count;
	}
	
	public List<Integer> getStudentIds() {
		PreparedStatement stmt = null;
		List<Integer> studentIds = new ArrayList<>();
		try {
			
			stmt = conn.prepareStatement(SQLConstants.SELECT_STUDENTID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				studentIds.add(rs.getInt("studentid"));
			}
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return studentIds;
	}
	
	
	public List<List<Integer>> getStudentCourseData(int studentId) {
		PreparedStatement stmt = null;
		List<List<Integer>> courseList = new ArrayList<>();
		try {
			
			stmt = conn.prepareStatement(SQLConstants.SELECT_COURSEMAPPING);
			stmt.setInt(1,studentId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(rs.getInt("courseid"));
				tmp.add(rs.getInt("coursecategory"));
				courseList.add(tmp);
			}
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return courseList;
	}
	
	@Override
	public void registerCourses(int studentId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.UPDATE_REGISTER);
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
	public void addCourses(StudentCourseMap studCoMap) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.ADD_COURSES);
			Map<Integer,Integer> map = studCoMap.getCourses();
			for (Map.Entry<Integer,Integer> entry : map.entrySet()) 
			{
				stmt.setLong(1, studCoMap.getStudentId());
				stmt.setInt(2, entry.getKey());
				stmt.setInt(3,entry.getValue());
				stmt.setInt(4, 0);
				stmt.executeUpdate();
			}
	        
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}

	public int getCoursePreference(long studentId,int courseId) {
		PreparedStatement stmt = null;
		int coursePref = -1;
		try {
			stmt = conn.prepareStatement(SQLConstants.COURSE_PREFERENCE);
			stmt.setLong(1, studentId);
			stmt.setInt(2, courseId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				coursePref = rs.getInt("coursecategory");
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return coursePref;
	}
	
//	public static void main(String[] args) {
//		studentDao.getModifiedList(2, 5);
//	}
	
	//Get all the courses that are registered in studentcoursemapping table
	public Set<Integer> getModifiedList(long studentid,int courseId){
		PreparedStatement stmt = null;
		Set<Integer> courses = new HashSet<>();
		try {
			stmt = conn.prepareStatement(SQLConstants.SELECT_COURSEID);
			stmt.setLong(1, studentid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				courses.add(rs.getInt("courseid"));
			}
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
	public int dropCourses(long studentId,int courseId,Set<Integer> courses) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int coursePref = -1;
		try {
			
			coursePref = getCoursePreference(studentId,courseId);
			stmt = conn.prepareStatement(SQLConstants.DELETE_COURSE);
			
			stmt.setLong(1,studentId);
			stmt.setInt(2, courseId);		
			
			stmt.executeUpdate();
			
			courses = getModifiedList(studentId,courseId);
			
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		return coursePref;
	}

	@Override
	public List<String> listCourse() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
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
	public void registerStudent(Student student) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.INSERT_STUDENT);
			
			stmt.setLong(1, student.getUserId());
			stmt.setString(2,student.getUserName());
			stmt.setString(3,student.getUserEmail());
			stmt.setString(4,student.getUserPassword());
			stmt.setLong(5,student.getUserPhonenumber());
			
			
			stmt.executeUpdate();
		}catch(SQLException se){
		      //Handle errors for JDBC
			
		      se.printStackTrace();
		   }
	}

//	public static void main(String[] args) {
//		studentDao.viewReportCard(1);
//	}
	@Override
	public List<GradeCard> viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		List<GradeCard> grades = new ArrayList<>();
		try {
			
			stmt = conn.prepareStatement(SQLConstants.GRADE_CARD);
			stmt.setInt(1,studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				GradeCard grade = new GradeCard();
				grade.setStudentId(studentId);
				grade.setCourseId(rs.getInt("courseId"));
				grade.setGrade(rs.getString("grade"));
				grades.add(grade);
//				System.out.println(grade.getGrade());
				}
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
//		System.out.println(grades.size());
		return grades;
	}

	@Override
	public boolean isApproved(int userId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQLConstants.IS_APPROVED);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				
				if(rs.getInt("studentId")==userId) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("userId");
		return false;
	}

	@Override
	public boolean searchStudent(long userId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQLConstants.SEARCH_STUDENT);
			stmt.setLong(1,userId);
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}

}
