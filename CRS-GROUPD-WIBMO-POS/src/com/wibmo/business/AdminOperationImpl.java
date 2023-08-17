package com.wibmo.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wibmo.bean.*;

import com.wibmo.dao.*;


public class AdminOperationImpl implements AdminOperation{

	Scanner scan = new Scanner(System.in);
	static AdminOperationImpl adminOp = new AdminOperationImpl();
	AdminDAO adminDAO = AdminDAOImpl.getInstance();
	StudentDAOImpl studentDAO = StudentDAOImpl.getInstance();
	@Override
	public void addCourse(long courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropCourse(long courseId) {
		// TODO Auto-generated method stub
		adminDAO.dropCourse(courseId);
	}

	@Override
	public boolean approveStudent() {
		// TODO Auto-generated method stub
		List<Integer> studentIds = studentDAO.getStudentIds();
		Map<Integer,Integer> courseCount = new HashMap<>();
		for(int studentId: studentIds) {
			List<List<Integer>> studentData = studentDAO.getStudentCourseData(studentId);
			for(List<Integer> course: studentData) {
				int studentCount = studentDAO.getCourseCount(course.get(0));
				if(studentCount<3) {
					/*
					 * 1. Course Cancel
					 * 		If alternatives available hai to usme se choose kar
					 * 		else student ko notify kar ki uska registration cancel ho gaya hai firse register kar
					 * */
				}
				else if(studentCount>10) {
					/*
					 *     If alternatives available hai to usme se choose kar
					 * 		else student ko notify kar ki uska registration cancel ho gaya hai firse register kar
					 * */
				}
				else {
					//approve and add them to grades table
					courseCount.getOrDefault(course.get(0),courseCount.getOrDefault(course.get(0),0)+1);
					adminDAO.setGradeCard(studentId,(int)course.get(0));
				}
			}
		}
		return false;
	}

	@Override
	public void addAdmin(Admin user) {
		// TODO Auto-generated method stub
		adminDAO.addAdmin(user);
	}

	@Override
	public void assignCoursesProf(int professorId, int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminRegistration(Admin user) {
		// TODO Auto-generated method stub
		adminOp.addAdmin(user);
	}

}
