/**
 * 
 */
package com.wibmo.service;

import java.util.ArrayList;
import java.util.HashMap;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.Student;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.entity.User;
import com.wibmo.repository.*;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;

/**
 * 
 */

@Service
public class StudentOperationImpl implements StudentOperation{
	
	
	
<<<<<<< HEAD
	
	@Autowired
	public StudentRepository studentDao;
	@Autowired
	public CourseRepository course;
=======
//	public Logger log=Logger.getLogger(StudentOperationImpl.class.getName());
	
	@Autowired
	private StudentRepository studentDao;
	@Autowired
	private CourseRepository courseDao;
>>>>>>> d9f69a998a971e6389e6f88834a69ba04323332a
	
	@Autowired
	private StudentOperation studentOp;
	
	@Override
	public void registerCourses(int studentId) {
		// TODO Auto-generated method stub
		//Logic will be implemented in ADMIN Panel
		
		studentDao.registerCourses(studentId);
	}
	
	@Override
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
			if(courseDao.findById(courseId)==null)
			{
				throw new CourseNotFoundException(courseId);
			}
		}
		int studentId = studentCoMap.getStudentId();
		for(Map.Entry<Integer, Integer> entry:studentCoMap.getCourses().entrySet())
			studentDao.AddSingleCourse(studentId, entry.getKey(), entry.getValue());
	}
	
	@Override
	public int dropCourses(int studentId,int courseId) throws CourseNotFoundException,UserNotFoundException {
		// TODO Auto-generated method stub
		Set<Integer> courses = new HashSet<>();
		if(studentDao.findById(studentId)==null)
		{
			throw new UserNotFoundException(studentId);
		}
		if(courseDao.findById(courseId)==null)
		{
			throw new CourseNotFoundException(studentId);
		}
		int coursePref = studentDao.findCoursePreference(studentId,courseId);
		studentDao.dropCourses(studentId,courseId);
		return coursePref;
	}

	@Override
	public Map<Integer,String> listCourse(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		int isPresent = studentDao.isApproved(studentId);
		if(isPresent>0)
		{
			throw new UserNotApprovedException(studentId);
		}
		
		Map<Integer,String> courses = studentDao.listCourse(studentId);
//		if(courses.size()==0)
//		{
//			log.info("Course Registration pending");
//			
//		}
//		log.info("List of Courses Approved");
		return courses;
		
	}

	@Override
	public List<CourseCatalog> viewCourseCatalog() {
		Iterable<CourseCatalog>courses= courseDao.findAll();
		List<CourseCatalog> list = new ArrayList<>();
		courses.forEach(course->list.add(course));
		return list;
	}

	@Override
	public void registerStudent(User user)throws StudentAlreadyRegisteredException {
		// TODO Auto-generated method stub
		Student student = new Student();

		if(studentDao.findByEmail(user.getUserEmail())==null)
		{
			throw new StudentAlreadyRegisteredException(user.getUserEmail());
		}
		student.setUserName(user.getUserName());
		student.setUserEmail(user.getUserEmail());
		student.setUserPhonenumber(user.getUserPhonenumber());
		student.setUserPassword(user.getUserPassword());
		studentDao.save(student);
	}


	@Override
	public List<GradeCard> viewReportCard(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		if(studentDao.isApproved(studentId)==false)
		{
			throw new UserNotApprovedException(studentId);
		}
		List<GradeCard> grades = studentDao.viewReportCard(studentId);
		return grades;
//		log.info(grades.size());
//		log.info("Your Grades");
//		log.info(String.format("%20s %20s", "CourseId","Grade"));
//		grades.forEach(grade->log.info(String.format("%20s %20s\n"
//				, grade.getCourseId(),
//				grade.getGrade())));
	}

	@Override
	public boolean isApproved(int userId) {
		// TODO Auto-generated method stub
		boolean flag= studentDao.isApproved(userId);
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
