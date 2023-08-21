package com.wibmo.business;

import java.util.List;


import com.wibmo.bean.User;
import com.wibmo.exception.*;
import com.wibmo.dao.*;

public interface ProfessorOperation {
	
	
  public void setGrades(long studentId,long courseId,String grade) throws UserNotFoundException,CourseNotFoundException ;
  
  public boolean requestCourseOffering(int professorid,List<Long> courseIdList) throws CourseNotFoundException;
  
  public void viewStudentList(long courseId) throws CourseNotFoundException;
  
  public  void viewCourseCatalog();

  public void registerProfessor(User user)throws UserAlreadyExistsException;
  
}
