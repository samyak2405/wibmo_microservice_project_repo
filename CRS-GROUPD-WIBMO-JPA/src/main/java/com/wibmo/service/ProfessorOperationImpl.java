/**
 * 
 */
package com.wibmo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.Professor;
import com.wibmo.entity.ProfessorCourseMap;
import com.wibmo.entity.Student;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.entity.User;
import com.wibmo.repository.*;
import com.wibmo.exception.*;


/**
 * 
 */

@Service
public class ProfessorOperationImpl implements ProfessorOperation{
	
	@Autowired
	ProfessorRepository professorDao;	
	@Autowired
	StudentRepository studentDao;
	@Autowired
	CourseRepository courseDao;
	
	@Autowired
	private StudentCourseMappingRepository studentCourseRepo;
	
	@Autowired
	private ProfessorCourseMappingRepository professorCoMapRepo;
	
	@Override
	@Transactional
	public void setGrades(int studentId, int courseId,String grade)throws UserNotFoundException,CourseNotFoundException
	{
		
		if(courseDao.findById(courseId)==null)
		{
			throw new CourseNotFoundException(courseId);
		}
		else if(studentDao.findById(studentId)==null)
		{
			throw new UserNotFoundException(studentId);
		}
		
		professorDao.setGrades(grade,studentId, courseId);
	}
	
	@Override
	@Transactional
	public void requestCourseOffering(int professorid,List<Integer> courseIdList)throws CourseNotFoundException {

        // TODO Auto-generated method stub
		for(Integer courseId:courseIdList)
		{
			if(courseDao.findById(courseId)==null)
				throw new CourseNotFoundException(courseId);
		}
		
		
		
		courseIdList.forEach((courseId)->
		{
			ProfessorCourseMap profCoMap = new ProfessorCourseMap();
			profCoMap.setProfessor(professorDao.findById(professorid).get());
			profCoMap.setCourseCatalog(courseDao.findById(courseId).get());
			profCoMap.setIsApproved(0);
			professorCoMapRepo.save(profCoMap);
			System.out.println("Done");
		});	
		return;
    }

	@Override
	public List<Student> viewStudentList(Integer courseId) throws CourseNotFoundException{
		
		if(courseDao.findById(courseId)==null)
		{
			throw new CourseNotFoundException(courseId);
		}
		List<StudentCourseMap> studentCo = studentCourseRepo.findByCourse(
				courseDao.findById(courseId).get()
				);
		
		List<Student> students=studentCo.stream()
				.map(studentMap->new Student(studentMap.getStudent()))
				.collect(Collectors.toList());
		    return students; 
    }
	
	@Override
	public List<CourseCatalog> viewCourseCatalog() {
		Iterable<CourseCatalog>courses= courseDao.findAll();
		List<CourseCatalog> list = new ArrayList<>();
		courses.forEach(list::add);
		return list;
	}




	@Override
	@Transactional
	public void registerProfessor(User user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		Professor professor = new Professor();
		if(professorDao.findProfessorByEmail(user.getUserEmail())>0)
		{
			throw new UserAlreadyExistsException(user.getUserEmail());
		}
		System.out.println("123");
		professor.setUserId(user.getUserId());
		professor.setUserName(user.getUserName());
		professor.setUserEmail(user.getUserEmail());
		professor.setUserPhonenumber(user.getUserPhonenumber());
		professor.setUserPassword(user.getUserPassword());
		professorDao.save(professor);

	}

	@Override
	
	public int getProfessorById(String userEmail) {
		// TODO Auto-generated method stub
		return professorDao.getProfessorById(userEmail);
		
	}

	@Override
	public Map<Integer,String> listOfApprovedCourses(int userId) {
		// TODO Auto-generated method stub

		List<Object[]> list = professorDao.listOfApprovedCourses(userId);
		Map<Integer,String> courses = new HashMap<>();
		for(Object[] result:list) 
			courses.put((Integer)result[0],(String)result[1]);
		return courses;
	}

	
	
}
