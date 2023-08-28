/**
 * 
 */
package com.wibmo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wibmo.model.CourseCatalog;
import com.wibmo.model.GradeCard;
import com.wibmo.model.Student;
import com.wibmo.model.StudentCourseMap;
import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */

@Repository
public class StudentDAOImpl implements StudentDAO{
	
//	@Autowired
//	public Logger log;
	
	Connection conn = DButils.getConnection();
		
	public int getStudentCourseCount(int courseid) {
		PreparedStatement stmt = null;
		int count = 0;
		try {
			
			stmt = conn.prepareStatement(SQLConstants.COUNT_STUDENT_COURSES);
			stmt.setInt(1,courseid);
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
				studentIds.add(rs.getInt("uniqueStudent"));
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
			System.out.println();
			System.out.println("'Student has successfully applied for registration'");
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}
	
	public void AddSingleCourse(int studentId,int courseId,int coursePref) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQLConstants.ADD_COURSES);
			stmt.setLong(1, studentId);
			stmt.setInt(2, courseId);
			stmt.setInt(3, coursePref);
			stmt.setInt(4, 0);
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
	public Map<Integer,String> listCourse(int studentid) {
		PreparedStatement stmt = null;
		Map<Integer,String> courses = new HashMap<>();
		
		try {
			stmt = conn.prepareStatement(SQLConstants.LIST_STUDENT_REG_COURSES);
			stmt.setInt(1,studentid);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				courses.put(rs.getInt("courseId"),rs.getString("courseName"));
				}
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		return courses;
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
			
			stmt.setString(1,student.getUserName());
			stmt.setString(2,student.getUserEmail());
			stmt.setString(3,student.getUserPassword());
			stmt.setLong(4,student.getUserPhonenumber());
			
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
//				log.info(grade.getGrade());
				}
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
//		log.info(grades.size());
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
		//log.info("userId");
		return false;
	}

	
	public int isStudentRegistered(int studentId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int isRegistered = 0;
		try {
			stmt = conn.prepareStatement(SQLConstants.SELECT_REGISTER);
			stmt.setLong(1,studentId);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				isRegistered = rs.getInt("isRegister");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isRegistered;
	}

	public int isRegistrationApproved(int studentId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int isApproved = 0;
		try {
			stmt = conn.prepareStatement(SQLConstants.SELECT_STUDENT_APPROVED);
			stmt.setLong(1,studentId);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				isApproved = rs.getInt("studentId");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isApproved;
		
	}

	@Override
	public int getStudentByEmail(String userEmail) {
		PreparedStatement stmt = null;
		int studentId = 0;
		try {
			stmt = conn.prepareStatement(SQLConstants.SELECT_STUDENT_BY_EMAIL);
			stmt.setString(1,userEmail);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				studentId = rs.getInt("studentid");
				System.out.println(studentId);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentId;
	}
	
	@Override
	public boolean searchStudentByID(int studentId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQLConstants.SEARCH_STUDENT_BY_ID);
			stmt.setInt(1,studentId);
			
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

	@Override
	public boolean doesEmailExist(String userEmail) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SQLConstants.STUDENT_BY_EMAIL);
			stmt.setString(1,userEmail);
			
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				return count>0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Map<Integer, String> getAddedCourses(int userId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		Map<Integer,String> map = new HashMap<>();
		try {
			stmt = conn.prepareStatement(SQLConstants.SELECT_ADDED_COURSE);
			stmt.setInt(1,userId);
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt("courseid"),rs.getString("courseName"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
