/**
 * 
 */
package com.wibmo.constant;

/**
 * 
 */
public class SQLConstants {
	public static final String IS_REGISTER=" UPDATE studentcoursemapping SET isRegister=1 WHERE userId=?1";
	public static final String COURSE_COUNT="SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE userId=?1";
	public static final String DELETE="DELETE FROM studentcoursemapping WHERE userId=? && courseId=?";
	public static final String SELECT_COURSE_CATEGORY="SELECT coursecategory FROM studentcoursemapping WHERE userId=:studentId AND courseId=:courseId";
	public static final String IS_APPROVED="SELECT COUNT(*) FROM gradecard where userId=?1";
	public static final String FIND_BY_EMAIL="SELECT COUNT(*) FROM student WHERE userEmail=?1";
	public static final String GET_STUDENTID="SELECT DISTINCT(userId) as uniqueStudent FROM studentcoursemapping";
	public static final String IS_STUDENT_REGISTERED="SELECT COUNT(*) FROM studentcoursemapping WHERE userId=:studentId AND isRegister=1";
	public static final String LIST_COURSE="SELECT gradecard.courseId, courseCatalog.courseName FROM "
			+ "gradecard as gradecard INNER JOIN coursecatalog as courseCatalog ON"
			+ " gradecard.courseId=courseCatalog.courseId WHERE gradecard.userId=?1";
	public static final String GET_ADDEDCOURSES="SELECT c.courseId,c.courseName FROM coursecatalog c INNER JOIN crs.studentcoursemapping scm ON c.courseId=scm.courseId WHERE scm.userId=?1";
	
}
