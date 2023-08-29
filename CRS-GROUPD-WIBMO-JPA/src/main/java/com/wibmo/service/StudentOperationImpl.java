/**
 * 
 */
package com.wibmo.service;

import java.util.ArrayList;


import java.util.HashMap;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wibmo.dto.AddCourseDto;
import com.wibmo.dto.SendCourseDto;
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

	@Autowired
	public StudentRepository studentDao;
	@Autowired
	public CourseRepository course;

	@Autowired
	private CourseRepository courseDao;
	
	@Autowired
	private StudentCourseMappingRepository studCoMapRepo;

	
	@Override
	public void registerCourses(int studentId) {
		// TODO Auto-generated method stub
		//Logic will be implemented in ADMIN Panel
		
		studentDao.registerCourses(studentId);
	}

	@Override
	@Transactional
	public void addCourses(int userId,AddCourseDto addCourseDto) throws CourseNotFoundException,CourseLimitExceededException {
		
		if(studentDao.getCourseCount(userId)>6)
		{
			throw new CourseLimitExceededException();
		}
		for(Map.Entry<Integer,Integer>entry:addCourseDto.getCourses().entrySet())
		{
			int courseId=entry.getKey();
			int pref=entry.getValue();
			if(courseDao.findById(courseId)==null)
			{
				throw new CourseNotFoundException(courseId);
			}
		}
		

		for(Map.Entry<Integer, Integer> entry:addCourseDto.getCourses().entrySet())
		{
			StudentCourseMap studCoMapping = new StudentCourseMap();
			studCoMapping.setStudent(studentDao.findById(userId).get());
			
			studCoMapping.setCourse(courseDao.findById(entry.getKey()).get());
			
			studCoMapping.setCoursePref(entry.getValue());
			studCoMapping.setIsRegister(0);
			studCoMapRepo.save(studCoMapping);
		}
			
	}
	
	@Override
	@Transactional
	public int dropCourses(int studentId,int courseId) throws CourseNotFoundException,UserNotFoundException {
		// TODO Auto-generated method stub

		if(studentDao.findById(studentId)==null)
		{
			throw new UserNotFoundException(studentId);
		}
		if(courseDao.findById(courseId)==null)
		{
			throw new CourseNotFoundException(studentId);
		}
		Integer isRegister= studentDao.findCoursePreference(studentId,courseId);
		studentDao.dropCourses(studentId,courseId);
		
		return isRegister;
	}

	@Override
	public Map<Integer,String> listCourse(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		int isPresent = studentDao.isApproved(studentId);
		if(isPresent<1)
			throw new UserNotApprovedException(studentId);
		
		List<Object[]> list = studentDao.listCourse(studentId);
		Map<Integer,String> courses = new HashMap<>();
		for(Object[] result:list) {
			courses.put((Integer)result[0],(String)result[1]);
		}
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

		if(studentDao.findByEmail(user.getUserEmail())>0)
		{
			throw new StudentAlreadyRegisteredException(user.getUserEmail());
		}
		student.setUserName(user.getUserName());
		student.setUserEmail(user.getUserEmail());
		student.setUserPhonenumber(user.getUserPhonenumber());
		student.setUserPassword(user.getUserPassword());
		student.setUserId(user.getUserId());
		studentDao.save(student);
		
	}


	@Override
	public Map<Integer,Map<Integer,String>> viewReportCard(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		if(studentDao.isApproved(studentId)<1)
		{
			throw new UserNotApprovedException(studentId);
		}
		List<Object[]> results = studentDao.viewReportCard(studentId);
		 Map<Integer,Map<Integer,String>> grades  = new HashMap<>();
		 for(Object[] result:results) {
			 if(!grades.containsKey((Integer)studentId)) {
				 grades.put((Integer)studentId, new HashMap<>());
			 }
			 grades.get(studentId).put((Integer)result[0], (String)result[1]);
		 }
		return grades;

	}

	@Override
	public int isApproved(int userId) {
		// TODO Auto-generated method stub
		
		int flag= studentDao.isApproved(userId);
		
		return flag;
	}

	@Override
	public int getStudentByEmail(String userEmail) {
		int studentId = studentDao.findByEmail(userEmail);
		return studentId;
	}

	@Override
	public Map<Integer, String> getAddedCourses(int userId) {
		// TODO Auto-generated method stub
		List<Object[]> list = studentDao.getAddedCourses(userId);
		Map<Integer,String> map = new HashMap<>();
		for(Object[] result:list)
			map.put((Integer)result[0], (String)result[1]);
		return map;
	}

	@Override
	public Integer isStudentRegistered(int userId) {
		// TODO Auto-generated method stub
		Integer isRegistered = studentDao.isStudentRegistered(userId);
		return isRegistered;
	}

}
