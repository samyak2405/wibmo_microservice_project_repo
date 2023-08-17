/**
 * 
 */
package com.wibmo.dao;

import java.util.List;

import com.wibmo.bean.Admin;

/**
 * 
 */
public interface AdminDAO {
	public void addCourse(long courseId);
	 public void dropCourse(long courseId);
	 public boolean approveStudent(long studentId,List<Long> courses);
	 public void addAdmin(Admin admin);
	 public void assignCoursesProf(int professorId,int courseId);
	public void setGradeCard(int studentId, int courseId);
	public List<Integer> getProfCourseData(int courseId);
	public List<Integer> getListOfCourses();
	public void setProfCourse(int professorid, int courseid);
}
