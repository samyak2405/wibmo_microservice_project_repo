/**
 * 
 */
package com.wibmo.business;

import java.util.List;

import com.wibmo.bean.CourseCatalog;
import com.wibmo.dao.*;

/**
 * 
 */
public class ProfessorOperationImpl implements ProfessorOperation{

	ProfessorDAO professorDao=ProfessorDAOImpl.getInstance();	
	
	
	@Override
	public void setGrades(long studentId, long courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean requestCourseOffering(List<Long> courseIdList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewStudentList(long courseId) {
		// TODO Auto-generated method stub
		
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

}
