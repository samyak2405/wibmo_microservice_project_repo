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
	 */
	public static final String VERIFY_STUDENT = " SELECT * FROM student WHERE userEmail=?1";
	public static final String UPDATE_PASSWORD_STUDENT = "UPDATE student SET userPassword=?2 WHERE userEmail=?1";

	public static final String VERIFY_PROFESSOR = " SELECT * FROM professor WHERE userEmail=?1";
	public static final String UPDATE_PASSWORD_PROFESSOR = "UPDATE professor SET userPassword=?2 WHERE userEmail=?1";

	public static final String VERIFY_ADMIN = " SELECT * FROM admin WHERE userEmail=?1";
	public static final String UPDATE_PASSWORD_ADMIN = "UPDATE admin SET userPassword=?2 WHERE userEmail=?1";

	
	public static final String STUDENT_BY_EMAIL = "SELECT COUNT(*) FROM student WHERE userEmail=?1";
	public static final String SEARCH_PROFESSOR = "SELECT COUNT(*) FROM professor WHERE userEmail=?1";
	public static final String SEARCH_ADMIN = "SELECT COUNT(*) FROM admin WHERE userEmail=?1";
	
	
}
	
	