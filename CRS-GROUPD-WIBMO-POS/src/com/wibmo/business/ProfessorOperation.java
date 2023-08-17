package com.wibmo.business;

import java.util.List;

import com.wibmo.bean.User;

public interface ProfessorOperation {
	
	
  public void setGrades(long studentId,long courseId,String grade);
  
  public boolean requestCourseOffering(int professorid,List<Long> courseIdList);
  
  public void viewStudentList(long courseId);
  
  public  void viewCourseCatalog();

  public void registerProfessor(User user);
  
}
