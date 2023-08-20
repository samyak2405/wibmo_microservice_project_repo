/**
 * 
 */
package com.wibmo.business;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.GradeCard;
import com.wibmo.bean.Student;
import com.wibmo.bean.StudentCourseMap;
import com.wibmo.bean.User;
import com.wibmo.dao.*;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;

/**
 * 
 */
public class StudentOperationImpl implements StudentOperation{
	
	StudentDAO studentDao = StudentDAOImpl.getInstance();
	CourseDAO course=CourseDAOImpl.getInstance();
	
	
	public static StudentOperationImpl studentOp = new StudentOperationImpl();
	
	@Override
	public void registerCourses(int studentId) {
		// TODO Auto-generated method stub
		//Logic will be implemented in ADMIN Panel
		studentDao.registerCourses(studentId);
	}

	public void AddSingleCourse(int studentId,int courseId,int coursePref) {
		studentDao.AddSingleCourse(studentId, courseId, coursePref);
	}
	@Override
	public void addCourses(StudentCourseMap studentCoMap) throws CourseNotFoundException,CourseLimitExceededException {
		
		Map<Integer,Integer>map=studentCoMap.getCourses();
		if(studentDao.getCourseCount(studentCoMap.getStudentId())>6)
		{
			throw new CourseLimitExceededException();
		}
		for(Map.Entry<Integer,Integer>entry:map.entrySet())
		{
			int courseId=entry.getKey();
			int pref=entry.getValue();
			if(course.searchCourse(courseId)==false)
			{
				throw new CourseNotFoundException(courseId);
			}
		}
		studentDao.addCourses(studentCoMap);
	}


	
	@Override
	public int dropCourses(int studentId,int courseId) throws CourseNotFoundException,UserNotFoundException {
		// TODO Auto-generated method stub
		Set<Integer> courses = new HashSet<>();
		if(studentDao.searchStudentByID(studentId)==false)
		{
			throw new UserNotFoundException(studentId);
		}
		if(course.searchCourse(courseId)==false)
		{
			throw new CourseNotFoundException(studentId);
		}
		
		int coursePref = studentDao.dropCourses(studentId,courseId,courses);
		
		return coursePref;
	}

	@Override
	public void listCourse(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		if(studentDao.isApproved(studentId)==false)
		{
			throw new UserNotApprovedException(studentId);
		}
		Map<Integer,String> courses = studentDao.listCourse(studentId);
		if(courses.size()==0)
		{
			System.out.println("Course Registration pending");
			return;
		}
		System.out.println("List of Courses Approved");
		for(Map.Entry<Integer, String> entry: courses.entrySet()) {
			System.out.println(String.format("%20s %20s\n", entry.getKey(),entry.getValue()));
		}
	}

	@Override
	public void viewCourseCatalog() {
		// TODO Auto-generated method stub
		List<CourseCatalog> courses = studentDao.viewCourseCatalog();
		System.out.println("Course Catalog: ");
		
		courses.forEach(course->System.out.println(String.format("%10s %25s %25s %30s\n"
				, course.getCourseId()
				,course.getCourseName()
				,course.getProfessorName()
				,course.getPrerequisites()
				)));
		
	}

	@Override
	public void registerStudent(User user)throws StudentAlreadyRegisteredException {
		// TODO Auto-generated method stub
		Student student = new Student();

		if(studentDao.doesEmailExist(user.getUserEmail()))
		{
			throw new StudentAlreadyRegisteredException(user.getUserEmail());
		}
		student.setUserName(user.getUserName());
		student.setUserEmail(user.getUserEmail());
		student.setUserPhonenumber(user.getUserPhonenumber());
		student.setUserPassword(user.getUserPassword());
		studentDao.registerStudent(student);
//		System.out.println(user.getUserPassword()+" "+user.getUserPassword().getClass().getName());
	}


	@Override
	public void viewReportCard(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		if(studentDao.isApproved(studentId)==false)
		{
			throw new UserNotApprovedException(studentId);
		}
		List<GradeCard> grades = studentDao.viewReportCard(studentId);
		System.out.println(grades.size());
		System.out.println("Your Grades");
		System.out.println(String.format("%20s %20s", "CourseId","Grade"));
		grades.forEach(grade->System.out.println(String.format("%20s %20s\n"
				, grade.getCourseId(),
				grade.getGrade())));
	}

	@Override
	public boolean isApproved(int userId) {
		// TODO Auto-generated method stub
		boolean flag=studentDao.isApproved(userId);
		return flag;
	}

	@Override
	public int getStudentByEmail(String userEmail) {
		int studentId = studentDao.getStudentByEmail(userEmail);
		return studentId;
	}

	@Override
	public Map<Integer, String> getAddedCourses(int userId) {
		// TODO Auto-generated method stub
		Map<Integer,String> map = studentDao.getAddedCourses(userId);
		return map;
	}

	@Override
	public int isStudentRegistered(int userId) {
		// TODO Auto-generated method stub
		int isRegistered = studentDao.isStudentRegistered(userId);
		return isRegistered;
	}

}
