/**
 * 
 */
package com.wibmo.constant;

/**
 * 
 */
public class SQLConstants {
		public static final String INSERT_STUDENT = "INSERT INTO student VALUES(?,?,?,?,?)";
		public static final String ADD_COURSES = "INSERT INTO studentcoursemapping VALUES(?,?,?)";
		public static final String DELETE_COURSE =  "DELETE FROM studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String GRADE_CARD = "SELECT courseId, grade FROM gradecard where studentId=?";
		public static final String COURSE_CATALOG = "SELECT * FROM coursecatalog";
		public static final String COURSE_PREFERENCE = "SELECT coursecategory FROM studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String SELECT_COURSEID = "SELECT courseid FROM studentcoursemapping WHERE studentid=?";
		public static final String VERIFY_STUDENT=" SELECT studentid,password FROM student WHERE studentid=?";
}
