/**
 * 
 */
package com.wibmo.service;

import java.util.List;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.Professor;
import com.wibmo.entity.Student;
import com.wibmo.entity.User;
import com.wibmo.repository.*;
import com.wibmo.exception.*;


/**
 * 
 */

@Service
public class ProfessorOperationImpl implements ProfessorOperation{

	private Logger log=LogManager.getLogger();
	@Autowired
	ProfessorRepository professorDao;	
	@Autowired
	StudentRepository studentDao;
	@Autowired
	CourseRepository courseDao;
	
	@Override
	public void setGrades(int studentId, int courseId,String grade)throws UserNotFoundException,CourseNotFoundException
	{
		
		if(courseDao.searchCourse(courseId)==false)
		{
			throw new CourseNotFoundException(courseId);
		}
		else if(studentDao.searchStudentByID(studentId)==false)
		{
			throw new UserNotFoundException(studentId);
		}
		professorDao.setGrades(studentId, courseId, grade);
	}
	
	@Override
	public boolean requestCourseOffering(int professorid,List<Integer> courseIdList)throws CourseNotFoundException {

        // TODO Auto-generated method stub
		for(Integer courseId:courseIdList)
		{
			if(courseDao.searchCourse(courseId)==false)
			{
				throw new CourseNotFoundException(courseId);
			}
		}
		
        professorDao.requestCourseOffering(professorid,courseIdList);
        return false;
    }

	@Override
	public List<Student> viewStudentList(Integer courseId) throws CourseNotFoundException{
		
		if(courseDao.searchCourse(courseId)==false)
		{
			throw new CourseNotFoundException(courseId);
		}
        List<Student>students=professorDao.viewStudentList(courseId);    
		
//		log.info("List of Students Registered For this particular course: ");
//		       students.forEach(course->log.info(String.format("%20s %20s %20s %20s\n"
//		
//		                , course.getUserId()
//		
//		                ,course.getUserName()
//		
//		                ,course.getUserEmail()
//		
//		                ,course.getUserPhonenumber()
//		
//		                )));
		      return students; 
    }
	@Override
	public List<CourseCatalog> viewCourseCatalog() {
		List<CourseCatalog>courses=professorDao.viewCourseCatalog();
		return courses;
//		log.info("Course Catalog: ");
//		
//		courses.forEach(course->log.info(String.format("%20s %20s %20s %20s\n"
//				, course.getCourseId()
//				,course.getCourseName()
//				,course.getProfessorName()
//				,course.getPrerequisites()
//				)));
//		
	}




	@Override
	public void registerProfessor(User user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		
		if(professorDao.searchProfessor(user.getUserEmail())>0)
		{
			throw new UserAlreadyExistsException(user.getUserEmail());
		}
		
		Professor professor = new Professor();
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
		Map<Integer,String> courses = professorDao.listOfApprovedCourses(userId);
//		for(Map.Entry<Integer, String> entry:courses.entrySet()) {
//			System.out.println(String.format("%20s %20s",entry.getKey(),entry.getValue()));
//		}
		return courses;
	}

	
	
}
