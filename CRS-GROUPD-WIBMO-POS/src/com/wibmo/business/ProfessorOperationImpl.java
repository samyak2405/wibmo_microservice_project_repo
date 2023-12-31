/**
 * 
 */
package com.wibmo.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.Professor;
import com.wibmo.bean.Student;
import com.wibmo.bean.User;
import com.wibmo.dao.*;
import com.wibmo.exception.*;


/**
 * Implementation of various Professor operations
 */
public class ProfessorOperationImpl implements ProfessorOperation{

	static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	ProfessorDAO professorDao=ProfessorDAOImpl.getInstance();	
	StudentDAO studentDao= StudentDAOImpl.getInstance();
	CourseDAO courseDao=CourseDAOImpl.getInstance();
	
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
