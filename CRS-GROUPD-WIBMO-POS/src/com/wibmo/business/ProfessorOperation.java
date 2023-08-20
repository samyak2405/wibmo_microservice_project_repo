package com.wibmo.business;

import java.util.List;


import com.wibmo.bean.User;
import com.wibmo.exception.*;
import com.wibmo.dao.*;

public interface ProfessorOperation {
	
	
  public void setGrades(int studentId,int courseId,String grade) throws UserNotFoundException,CourseNotFoundException ;
  
  public boolean requestCourseOffering(int professorid,List<Integer> courseIdList) throws CourseNotFoundException;
  
  public void viewStudentList(Integer courseId) throws CourseNotFoundException;
  
  public  void viewCourseCatalog();

  public void registerProfessor(User user)throws UserAlreadyExistsException;

public int getProfessorById(String userEmail);
  
}
