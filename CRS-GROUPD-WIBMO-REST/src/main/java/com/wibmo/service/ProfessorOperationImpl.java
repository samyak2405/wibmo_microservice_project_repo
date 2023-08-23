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

import com.wibmo.model.CourseCatalog;
import com.wibmo.model.Professor;
import com.wibmo.model.Student;
import com.wibmo.model.User;
import com.wibmo.repository.*;
import com.wibmo.exception.*;


/**
 * 
 */

@Service
public class ProfessorOperationImpl implements ProfessorOperation{

	private Logger log=LogManager.getLogger();
	@Autowired
	ProfessorDAO professorDao;	
	@Autowired
	StudentDAO studentDao;
	@Autowired
	CourseDAO courseDao;
	
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
	public void viewStudentList(Integer courseId) throws CourseNotFoundException{
		
		if(courseDao.searchCourse(courseId)==false)
		{
			throw new CourseNotFoundException(courseId);
		}
        List<Student>students=professorDao.viewStudentList(courseId);    
		
		log.info("List of Students Registered For this particular course: ");
		       students.forEach(course->log.info(String.format("%20s %20s %20s %20s\n"
		
		                , course.getUserId()
		
		                ,course.getUserName()
		
		                ,course.getUserEmail()
		
		                ,course.getUserPhonenumber()
		
		                )));
		       
    }
	@Override
	public void viewCourseCatalog() {
		List<CourseCatalog>courses=professorDao.viewCourseCatalog();
		
		log.info("Course Catalog: ");
		
		courses.forEach(course->log.info(String.format("%20s %20s %20s %20s\n"
				, course.getCourseId()
				,course.getCourseName()
				,course.getProfessorName()
				,course.getPrerequisites()
				)));
		
	}




	@Override
	public void registerProfessor(User user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		
		if(professorDao.searchProfessor(user.getUserEmail())==true)
		{
			throw new UserAlreadyExistsException(user.getUserEmail());
		}
		
		Professor professor = new Professor();
		professor.setUserName(user.getUserName());
		professor.setUserEmail(user.getUserEmail());
		professor.setUserPhonenumber(user.getUserPhonenumber());
		professor.setUserPassword(user.getUserPassword());
		professorDao.registerProfessor(professor);
		
	}

	@Override
	
	public int getProfessorById(String userEmail) {
		// TODO Auto-generated method stub
		return professorDao.getProfessorById(userEmail);
		
	}

	@Override
	public void listOfApprovedCourses(int userId) {
		// TODO Auto-generated method stub
		Map<Integer,String> courses = professorDao.listOfApprovedCourses(userId);
		for(Map.Entry<Integer, String> entry:courses.entrySet()) {
			System.out.println(String.format("%20s %20s",entry.getKey(),entry.getValue()));
		}
	}

	
	
}
