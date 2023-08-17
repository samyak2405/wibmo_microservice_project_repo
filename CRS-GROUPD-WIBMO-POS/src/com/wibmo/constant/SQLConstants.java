/**
 * 
 */
package com.wibmo.constant;

/**
 * 
 */
public class SQLConstants {

	
		/*
		 * Student
		 */
		public static final String INSERT_STUDENT = "INSERT INTO crs.student VALUES(?,?,?,?,?)";
		public static final String ADD_COURSES = "INSERT INTO crs.studentcoursemapping VALUES(?,?,?)";
		public static final String DELETE_COURSE =  "DELETE FROM crs.studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String GRADE_CARD = "SELECT courseId, grade FROM crs.gradecard where studentId=?";
		public static final String COURSE_CATALOG = "SELECT * FROM crs.coursecatalog";
		public static final String COURSE_PREFERENCE = "SELECT coursecategory FROM crs.studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String SELECT_COURSEID = "SELECT courseid FROM crs.studentcoursemapping WHERE studentid=?";
		public static final String VERIFY_STUDENT=" SELECT studentid,password FROM crs.student WHERE studentid=?";
		
		
		
		/*
		 * Professor
		 */
		public static final String SET_GRADES = "UPDATE crs.gradecard SET grade=? WHERE studentId=? && courseId=?";
		public static final String VERIFY_PROFESSOR=" SELECT professorid,password FROM crs.professor WHERE professorid=?";
		public static final String STUDENT_LIST = "SELECT studentid,studentname, studentemail,phonenumber FROM crs.student"
                + " WHERE studentid IN (SELECT studentid FROM crs.studentcoursemapping WHERE courseid=?)";

		
		public static final String VERIFY_ADMIN=" SELECT adminId,password FROM crs.admin WHERE adminId=?";
		public static final String REQUEST_COURSE="INSERT INTO crs.professorcoursemapping VALUES(?,?)";
		
		
		
		
		

}
