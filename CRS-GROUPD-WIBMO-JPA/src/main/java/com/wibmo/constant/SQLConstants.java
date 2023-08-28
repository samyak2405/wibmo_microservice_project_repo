/**

 * 
 */
package com.wibmo.constant;

import org.springframework.stereotype.Component;

/**
 * Constants to store various SQL Queries.
 */
@Component
public class SQLConstants {
	
		/*
		 * Authentication Queries
		 * */
		public static final String VERIFY_STUDENT=" SELECT * FROM student WHERE userEmail=?1";
		public static final String UPDATE_PASSWORD_STUDENT="UPDATE student SET userPassword=?1 WHERE userEmail=?2";
		
		public static final String VERIFY_PROFESSOR=" SELECT * FROM professor WHERE userEmail=?1";
		public static final String UPDATE_PASSWORD_PROFESSOR ="UPDATE professor SET userPassword=?1 WHERE userEmail=?2";

		public static final String VERIFY_ADMIN=" SELECT * FROM admin WHERE userEmail=?1";
		public static final String UPDATE_PASSWORD_ADMIN="UPDATE admin SET userPassword=?1 WHERE userEmail=?2";
		
		/*
		 * Student Queries
		 * */
		public static final String SELECT_REGISTER = "SELECT isRegister FROM studentcoursemapping WHERE studentId=?";
		public static final String ADD_COURSES = "INSERT INTO studentcoursemapping VALUES(?,?,?,0)";
		public static final String COURSE_PREFERENCE = "SELECT coursecategory FROM studentcoursemapping WHERE studentId=? AND courseId=?";
		public static final String DELETE_COURSE =  "DELETE FROM studentcoursemapping WHERE studentId=? && courseId=?";
		public static final String LIST_STUDENT_REG_COURSES = "SELECT gradecard.courseId, courseCatalog.courseName FROM crs.gradecard as gradecard INNER JOIN crs.coursecatalog as courseCatalog ON gradecard.courseId=courseCatalog.courseId WHERE gradecard.studentId=?";
		
		public static final String UPDATE_REGISTER=" UPDATE studentcoursemapping SET isRegister=1 WHERE studentid=?";
		public static final String COUNT_COURSES = "SELECT COUNT(courseid) as courseCount FROM crs.studentcoursemapping WHERE studentId=?";
		public static final String IS_APPROVED = "SELECT COUNT(*) FROM gradecard where studentId=?";
		public static final String SELECT_STUDENTID = "SELECT DISTINCT(studentId) as uniqueStudent FROM studentcoursemapping";
		public static final String COUNT_STUDENT_COURSES = "SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE courseId=?";
		
		
		
		
		public static final String SELECT_COURSEID = "SELECT courseid FROM crs.studentcoursemapping WHERE studentid=?";
		public static final String COURSE_CATALOG = "SELECT * FROM coursecatalog";
		public static final String INSERT_STUDENT = "INSERT INTO crs.student (studentname,studentemail,password,phonenumber) VALUES(?,?,?,?)";
		public static final String GRADE_CARD = "SELECT courseId, grade FROM crs.gradecard where studentId=?";
		
		public static final String SELECT_STUDENT_BY_EMAIL = "SELECT studentid FROM crs.student WHERE studentemail=?";
		public static final String SEARCH_STUDENT_BY_ID = "SELECT * FROM student WHERE studentid=?";
		public static final String STUDENT_BY_EMAIL = "SELECT COUNT(*) FROM student WHERE userEmail=?1";
		public static final String SELECT_ADDED_COURSE = "SELECT c.courseid,c.courseName FROM coursecatalog c INNER JOIN crs.studentcoursemapping scm ON c.courseid=scm.courseId WHERE scm.studentid=?";
		/*
		 * Professor Queries
		 */
		public static final String SELECT_PROFESSOR_BY_EMAIL = "SELECT userId FROM professor WHERE userEmail=?";
		
		public static final String SET_GRADES = "UPDATE crs.gradecard SET grade=? WHERE studentId=? && courseId=?";
		public static final String REQUEST_COURSE="INSERT INTO professorcoursemapping VALUES(?,?,0)";
		public static final String STUDENT_LIST = "SELECT studentid,studentname, studentemail,phonenumber FROM crs.student"
                + " WHERE studentid IN (SELECT studentid FROM crs.studentcoursemapping WHERE courseid=?)";
		//JOIN CUSTOM QUERY
		public static final String LIST_APPROVED_COURSES = "SELECT pcm.courseid, cc.courseName FROM professorcoursemapping pcm INNER JOIN coursecatalog cc ON pcm.courseid=cc.courseId WHERE pcm.professorid=? && pcm.isApproved=1";
		public static final String SEARCH_PROFESSOR = "SELECT COUNT(*) FROM professor WHERE userEmail=?1";
		
				
		
		
		
		
		public static final String RECORD_PAYMENT = "INSERT INTO crs.payment VALUES(?,?,?,?)";
		
		/*
		 * Admin Queries
		 * */
		public static final String APPROVE_STUDENT = "UPDATE student SET isApproved=1";
		public static final String APPROVE_STUDENT_BY_ID = "UPDATE student SET isApproved=1 WHERE userId=?";
		public static final String SELECT_PROFESSORS_BY_ID = "SELECT DISTINCT professorId FROM professorcoursemapping";
		public static final String SELECT_PROFESSOR_COURSES = "SELECT courseId FROM professorcoursemapping WHERE professorId=?";
		public static final String APPROVE_PROFESSOR_COURSE = "UPDATE professorcoursemapping SET isApproved=1 WHERE professorId=? && courseId=?";
		public static final String SEARCH_ADMIN = "SELECT userId FROM admin WHERE userEmail=?";
		public static final String INSERT_GRADECARD = "INSERT INTO gradecard VALUES(?,?,NA)";
		public static final String SELECT_COURSEMAPPING = "SELECT courseId, coursecategory FROM studentcoursemapping WHERE studentId=?";
		
		
		
		
		
		
		public static final String DROP_COURSE =  "DELETE FROM crs.coursecatalog WHERE courseid=?";
		public static final String PROFESSOR_COURSE_DATA = "SELECT professorid FROM crs.professorcoursemapping WHERE courseid=?";
		public static final String LIST_COURSES = "SELECT courseId from crs.coursecatalog";
		public static final String PROF_COURSE_ASSIGNMENT="UPDATE crs.professorcoursemapping SET isassigned=1 WHERE professorId=? && courseId=?";
		public static final String PENDING_REGISTRATION = "SELECT studentid FROM student where isapproved=0";

		public static final String SEND_NOTIFICATION="INSERT INTO notificationstudentmapping VALUES(?,?)";
		public static final String SELECT_STUDENT_APPROVED = "SELECT studentId FROM crs.gradecard WHERE studentId=?";
		public static final String SELECT_ADMIN_BY_EMAIL = "SELECT adminId FROM crs.admin WHERE adminEmail=?";
		public static final String STUDENT_REGISTRATION_REJECTION = "DELETE FROM crs.studentcoursemapping WHERE studentid=?";
		
	/**
	 * Common for student And Professor
	 */
		public static final String GET_NOTIFICATION = "SELECT * FROM notification"
                + " WHERE notificationId IN (SELECT notifId FROM notificationstudentmapping WHERE studentId=?1)";

	    public static final String SEARCH_COURSE = "SELECT * FROM coursecatalog WHERE courseId=?";
		
		
	
		
		
		
		
		
	
	
	

		
		

}