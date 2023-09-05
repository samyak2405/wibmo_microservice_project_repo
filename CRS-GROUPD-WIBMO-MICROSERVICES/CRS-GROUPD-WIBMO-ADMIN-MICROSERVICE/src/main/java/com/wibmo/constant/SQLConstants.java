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
	 * Admin Queries
	 */
	public static final String APPROVE_STUDENT = "UPDATE student SET isApproved=1";
	
	public static final String APPROVE_STUDENT_BY_ID = "UPDATE student SET isApproved=1 WHERE userId=?";
	
	public static final String SELECT_PROFESSORS_BY_ID = "SELECT DISTINCT userId FROM professorcoursemapper";
	
	public static final String SELECT_PROFESSOR_COURSES = "SELECT courseId FROM professorcoursemapper WHERE userId=?1";
	
	public static final String SEARCH_ADMIN = "SELECT userId FROM admin WHERE userEmail=?";
	
	public static final String STUDENT_REGISTRATION_REJECTION = "DELETE FROM studentcoursemapper WHERE userId=?";
	
	public static final String FIND_BY_EMAIL = "SELECT COUNT(*) FROM admin WHERE userEmail=?1";
	
	public static final String SET_ADMIN_APPROVAL = "UPDATE admin SET isApproved=1 WHERE userId=?1";
	
	public static final String SEARCH_PROFESSOR = "SELECT COUNT(*) FROM professor WHERE userEmail=?1";
	
	public static final String SELECT_PROFESSOR_BY_EMAIL = "SELECT userId FROM professor WHERE userEmail=?";
	
	public static final String GET_STUDENT_IDS="SELECT DISTINCT(userId) as uniqueStudent FROM studentcoursemapping";
	
	public static final String STUDENT_REGISTERED = "SELECT COUNT(*) FROM studentcoursemapping WHERE userId=:studentId AND isRegister=1";
	
	public static final String COURSE_REGISTRATION_APPROVED = "SELECT COUNT(*) FROM gradecard where userId=?1";
	
	public static final String GET_STUDENT_COURSE_DATA = "SELECT courseId, coursecategory FROM studentcoursemapping WHERE userId=?";
	
	public static final String STUDENT_COURSE_COUNT = "SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE courseId=?";

	public static final String LIST_PROFESSOR_IDS = "SELECT DISTINCT userId FROM professorcoursemapping";
	
	public static final String GET_PROFESSOR_COURSES = "SELECT courseId FROM professorcoursemapping WHERE userId=?1";

	public static final String APPROVE_COURSE_PROFESSOR = "UPDATE professorcoursemapping SET isApproved=1 WHERE userId=?1 AND courseId=?2";
}