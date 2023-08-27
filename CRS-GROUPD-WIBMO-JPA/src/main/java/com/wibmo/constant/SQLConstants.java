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
		public static final String VERIFY_STUDENT=" SELECT studentemail,password,isapproved FROM crs.student WHERE studentemail=?";
		public static final String UPDATE_PASSWORD_STUDENT="UPDATE crs.student SET password=? WHERE studentemail=?";
		
		public static final String VERIFY_PROFESSOR=" SELECT professoremail,password FROM crs.professor WHERE professoremail=?";
		public static final String UPDATE_PASSWORD_PROFESSOR ="UPDATE crs.professor SET password=? WHERE professoremail=?";

		public static final String VERIFY_ADMIN=" SELECT adminEmail,password FROM crs.admin WHERE adminEmail=?";
		public static final String UPDATE_PASSWORD_ADMIN="UPDATE crs.admin SET password=? WHERE adminEmail=?";
		
		/*
		 * Student Queries
		 * */
		public static final String UPDATE_REGISTER=" UPDATE studentcoursemapping SET isRegister=1 WHERE studentid=?";
		public static final String ADD_COURSES = "INSERT INTO studentcoursemapping VALUES(?,?,?,?)";
		public static final String COUNT_COURSES = "SELECT COUNT(courseid) as courseCount FROM crs.studentcoursemapping WHERE studentId=?";
		public static final String DELETE_COURSE =  "DELETE FROM studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String COURSE_PREFERENCE = "SELECT coursecategory FROM studentcoursemapping WHERE studentid=? && courseid=?";
		public static final String IS_APPROVED = "SELECT COUNT(*) FROM gradecard where studentId=?";
		
		
		
		
		public static final String COUNT_STUDENT_COURSES = "SELECT COUNT(courseid) as courseCount FROM crs.studentcoursemapping WHERE courseid=?";
		public static final String SELECT_STUDENTID = "SELECT DISTINCT(studentid) as uniqueStudent FROM crs.studentcoursemapping";
		public static final String SELECT_COURSEMAPPING = "SELECT courseid, coursecategory FROM crs.studentcoursemapping WHERE studentid=?";
		
		public static final String SELECT_COURSEID = "SELECT courseid FROM crs.studentcoursemapping WHERE studentid=?";
		public static final String LIST_STUDENT_REG_COURSES = "SELECT gradecard.courseId, courseCatalog.courseName FROM crs.gradecard as gradecard INNER JOIN crs.coursecatalog as courseCatalog ON gradecard.courseId=courseCatalog.courseId WHERE gradecard.studentId=?";
		public static final String COURSE_CATALOG = "SELECT * FROM crs.coursecatalog";
		public static final String INSERT_STUDENT = "INSERT INTO crs.student (studentname,studentemail,password,phonenumber) VALUES(?,?,?,?)";
		public static final String GRADE_CARD = "SELECT courseId, grade FROM crs.gradecard where studentId=?";
		
		public static final String SELECT_REGISTER = "SELECT isRegister FROM crs.studentcoursemapping WHERE studentid=?";
		public static final String SELECT_STUDENT_BY_EMAIL = "SELECT studentid FROM crs.student WHERE studentemail=?";
		public static final String SEARCH_STUDENT_BY_ID = "SELECT * FROM student WHERE studentid=?";
		public static final String STUDENT_BY_EMAIL = "SELECT COUNT(*) FROM crs.student WHERE studentemail=?";
		public static final String SELECT_ADDED_COURSE = "SELECT c.courseid,c.courseName FROM coursecatalog c INNER JOIN crs.studentcoursemapping scm ON c.courseid=scm.courseId WHERE scm.studentid=?";
		/*
		 * Professor Queries
		 */
		public static final String SET_GRADES = "UPDATE crs.gradecard SET grade=? WHERE studentId=? && courseId=?";
		public static final String REQUEST_COURSE="INSERT INTO crs.professorcoursemapping VALUES(?,?,0)";
		public static final String STUDENT_LIST = "SELECT studentid,studentname, studentemail,phonenumber FROM crs.student"
                + " WHERE studentid IN (SELECT studentid FROM crs.studentcoursemapping WHERE courseid=?)";
		public static final String SEARCH_PROFESSOR = "SELECT COUNT(professoremail) FROM crs.professor WHERE professoremail=?";
		public static final String SELECT_PROFESSOR_BY_EMAIL = "SELECT professorid FROM crs.professor WHERE professoremail=?";
		
		//JOIN CUSTOM QUERY
		public static final String LIST_APPROVED_COURSES = "SELECT pcm.courseid, cc.courseName FROM professorcoursemapping pcm INNER JOIN coursecatalog cc ON pcm.courseid=cc.courseId WHERE pcm.professorid=? && pcm.isApproved=1";
		
		
		
		
		
		public static final String RECORD_PAYMENT = "INSERT INTO crs.payment VALUES(?,?,?,?)";
		public static final String SELECT_PROFESSORS_BY_ID = "SELECT DISTINCT professorid FROM crs.professorcoursemapping";
		public static final String SELECT_PROFESSOR_COURSES = "SELECT courseid FROM crs.professorcoursemapping WHERE professorid=?";
		public static final String APPROVE_PROFESSOR_COURSE = "UPDATE crs.professorcoursemapping SET isApproved=1 WHERE professorid=? && courseid=?";
		
		/*
		 * Admin Queries
		 * */
		public static final String INSERT_GRADECARD = "INSERT INTO crs.gradecard VALUES(?,?,?)";
		public static final String DROP_COURSE =  "DELETE FROM crs.coursecatalog WHERE courseid=?";
		public static final String INSERT_ADMIN = "INSERT INTO crs.admin VALUES(?,?,?,?,?,0)";
		public static final String PROFESSOR_COURSE_DATA = "SELECT professorid FROM crs.professorcoursemapping WHERE courseid=?";
		public static final String LIST_COURSES = "SELECT courseId from crs.coursecatalog";
		public static final String PROF_COURSE_ASSIGNMENT="UPDATE crs.professorcoursemapping SET isassigned=1 WHERE professorId=? && courseId=?";
		public static final String SEARCH_ADMIN = "SELECT * FROM admin WHERE adminId=?";
		public static final String APPROVE_STUDENT = "UPDATE crs.student SET isapproved=1";
		public static final String PENDING_REGISTRATION = "SELECT studentid FROM student where isapproved=0";
		public static final String APPROVE_STUDENT_BY_ID = "UPDATE crs.student SET isapproved=1 WHERE studentid=?";
		public static final String SEND_NOTIFICATION="INSERT INTO crs.notificationstudentmapping VALUES(?,?)";
		public static final String SELECT_STUDENT_APPROVED = "SELECT studentId FROM crs.gradecard WHERE studentId=?";
		public static final String SELECT_ADMIN_BY_EMAIL = "SELECT adminId FROM crs.admin WHERE adminEmail=?";
		public static final String STUDENT_REGISTRATION_REJECTION = "DELETE FROM crs.studentcoursemapping WHERE studentid=?";
		
	/**
	 * Common for student And Professor
	 */
		public static final String GET_NOTIFICATION = "SELECT * FROM crs.notification"
                + " WHERE notificationId IN (SELECT notifId FROM crs.notificationstudentmapping WHERE studentId=?)";

	    public static final String SEARCH_COURSE = "SELECT * FROM coursecatalog WHERE courseId=?";
		
		
	
		
		
		
		
		
	
	
	

		
		

}