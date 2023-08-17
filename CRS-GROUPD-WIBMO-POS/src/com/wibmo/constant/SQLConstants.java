/**
 * 
 */
package com.wibmo.constant;

/**
 * 
 */
public class SQLConstants {
		//student queries
		public static final String INSERT_STUDENT = "INSERT INTO crs.student VALUES(?,?,?,?,?)";
		public static final String ADD_COURSES = "INSERT INTO crs.studentcoursemapping VALUES(?,?,?,?)";
		public static final String DELETE_COURSE =  "DELETE FROM crs.studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String GRADE_CARD = "SELECT courseId, grade FROM crs.gradecard where studentId=?";
		public static final String COURSE_CATALOG = "SELECT * FROM crs.coursecatalog";
		public static final String COURSE_PREFERENCE = "SELECT coursecategory FROM crs.studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String SELECT_COURSEID = "SELECT courseid FROM crs.studentcoursemapping WHERE studentid=?";
		public static final String VERIFY_STUDENT=" SELECT studentid,password FROM crs.student WHERE studentid=?";
		public static final String UPDATE_REGISTER=" UPDATE crs.studentcoursemapping SET isRegister=1 WHERE studentid=?";
		public static final String SELECT_STUDENTID = "SELECT DISTINCT(studentid) as distinct FROM crs.studentcoursemapping";
		public static final String COUNT_COURSES = "SELECT COUNT(courseid) as courseCount FROM crs.GradeCard";
		
		//Admin Queries
		public static final String INSERT_ADMIN = "INSERT INTO crs.admin VALUES(?,?,?,?,?)";
		public static final String SELECT_COURSEMAPPING = "SELECT courseid, coursecategory FROM crs.studentcoursemapping WHERE studentid=?";
		public static final String INSERT_GRADECARD = "INSERT INTO crs.gradecard VALUES(?,?,?)";
		public static final String DROP_COURSE =  "DELETE FROM crs.coursecatalog WHERE courseid=?";

}
