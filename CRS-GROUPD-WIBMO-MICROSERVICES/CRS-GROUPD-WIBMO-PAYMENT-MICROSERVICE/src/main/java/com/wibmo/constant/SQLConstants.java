/**
 * 
 */
package com.wibmo.constant;

/**
 * 
 */
public class SQLConstants {
	
	public static final String IS_APPROVED="SELECT COUNT(*) FROM gradecard where userId=?1";
	public static final String FIND_BY_EMAIL="SELECT COUNT(*) FROM student WHERE userEmail=?1";
	
	public static final String IS_STUDENT_REGISTERED="SELECT COUNT(*) FROM studentcoursemapping WHERE userId=:studentId AND isRegister=1";
	
}
