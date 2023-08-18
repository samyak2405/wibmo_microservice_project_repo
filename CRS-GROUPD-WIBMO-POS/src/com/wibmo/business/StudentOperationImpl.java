/**
 * 
 */
package com.wibmo.business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.GradeCard;
import com.wibmo.bean.Student;
import com.wibmo.bean.StudentCourseMap;
import com.wibmo.bean.User;
import com.wibmo.dao.*;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotFoundException;

/**
 * 
 */
public class StudentOperationImpl implements StudentOperation{
	
	StudentDAO studentDao = StudentDAOImpl.getInstance();
	
	public static StudentOperationImpl studentOp = new StudentOperationImpl();
	
	@Override
	public void registerCourses(int studentId) {
		// TODO Auto-generated method stub
		//Logic will be implemented in ADMIN Panel
		studentDao.registerCourses(studentId);
	}

	@Override
	public void addCourses(StudentCourseMap studCoMap) throws DuplicateCourseEntryException {
		
		// TODO Auto-generated method stub
		studentDao.addCourses(studCoMap);
	}


	
	@Override
	public int dropCourses(long studentId,int courseId) throws CourseNotFoundException,UserNotFoundException {
		// TODO Auto-generated method stub
		Set<Integer> courses = new HashSet<>();
		
		int coursePref = studentDao.dropCourses(studentId,courseId,courses);
		
//		List<CourseCatalog> coursesList = studentDao.viewCourseCatalog();
//		System.out.println(coursesList.size());
//		System.out.println("Choose courses from below given list");
//		coursesList.forEach(courseCatalog->{
//			if(!courses.contains(courseCatalog.getCourseId()))
//			{
//				System.out.println(String.format("%20s %20s %20s %20s\n"
//						, courseCatalog.getCourseId()
//						,courseCatalog.getCourseName()
//						,courseCatalog.getProfessorName()
//						,courseCatalog.getPrerequisites()
//						));
//			}
//		});
		return coursePref;
	}

	@Override
	public void listCourse() {
		// TODO Auto-generated method stub
		List<String> courses = studentDao.listCourse();
		System.out.println("List of Courses Approved");
		courses.forEach((course)->System.out.println(String.format("%20s\n",course)));
	}

	@Override
	public void viewCourseCatalog() {
		// TODO Auto-generated method stub
		List<CourseCatalog> courses = studentDao.viewCourseCatalog();
		System.out.println("Course Catalog: ");
		
		courses.forEach(course->System.out.println(String.format("%20s %20s %20s %20s\n"
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
		student.setUserId(user.getUserId());
		student.setUserName(user.getUserName());
		student.setUserEmail(user.getUserEmail());
		student.setUserPhonenumber(user.getUserPhonenumber());
		student.setUserPassword(user.getUserPassword());
		
		studentDao.registerStudent(student);
//		System.out.println(user.getUserPassword()+" "+user.getUserPassword().getClass().getName());
	}


	@Override
	public void viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		List<GradeCard> grades = studentDao.viewReportCard(studentId);
		System.out.println(grades.size());
		System.out.println("Your Grades");
		System.out.println(String.format("%20s %20s", "CourseId","Grade"));
		grades.forEach(grade->System.out.println(String.format("%20s %20s\n"
				, grade.getCourseId(),
				grade.getGrade())));
	}

}
