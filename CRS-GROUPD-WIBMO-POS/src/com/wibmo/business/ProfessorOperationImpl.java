/**
 * 
 */
package com.wibmo.business;

import java.util.List;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.bean.Professor;
import com.wibmo.bean.Student;
import com.wibmo.bean.User;
import com.wibmo.dao.*;

/**
 * 
 */
public class ProfessorOperationImpl implements ProfessorOperation{

	ProfessorDAO professorDao=ProfessorDAOImpl.getInstance();	
	
	
	@Override
	public void setGrades(long studentId, long courseId,String grade)  {
		
		professorDao.setGrades(studentId, courseId, grade);
	}
	
	
	

	@Override
	public boolean requestCourseOffering(int professorid,List<Long> courseIdList) {

        // TODO Auto-generated method stub

        professorDao.requestCourseOffering(professorid,courseIdList);

        return false;

    }

	@Override
	public void viewStudentList(long courseId) {

        List<Student>students=professorDao.viewStudentList(courseId);    

System.out.println("List of Students Registered For this particular course: ");
       students.forEach(course->System.out.println(String.format("%20s %20s %20s %20s\n"

                , course.getUserId()

                ,course.getUserName()

                ,course.getUserEmail()

                ,course.getUserPhonenumber()

                )));
    }

	@Override
	public void viewCourseCatalog() {
		List<CourseCatalog>courses=professorDao.viewCourseCatalog();	
		System.out.println("Course Catalog: ");
		
		courses.forEach(course->System.out.println(String.format("%20s %20s %20s %20s\n"
				, course.getCourseId()
				,course.getCourseName()
				,course.getProfessorName()
				,course.getPrerequisites()
				)));
		
	}




	@Override
	public void registerProfessor(User user) {
		// TODO Auto-generated method stub
		Professor professor = new Professor();
		professor.setUserId(user.getUserId());
		professor.setUserName(user.getUserName());
		professor.setUserEmail(user.getUserEmail());
		professor.setUserPhonenumber(user.getUserPhonenumber());
		professor.setUserPassword(user.getUserPassword());
		
		professorDao.registerProfessor(professor);
		
	}

}
