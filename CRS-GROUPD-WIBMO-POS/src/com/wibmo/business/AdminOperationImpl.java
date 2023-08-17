package com.wibmo.business;

import java.util.Collections;
import java.util.Comparator;
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
	
	
	public List<List<Integer>> sortByCoursePref(List<List<Integer>> list)
	{
		Collections.sort(list, new Comparator<List<Integer>>() {
			public int compare(List<Integer> a, List<Integer> b)
			{
				return a.get(1)-b.get(1);
			}
		});
		return list;
	}

	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		List<Integer> studentIds = studentDAO.getStudentIds();
		Map<Integer,Integer> courseCount = new HashMap<>();
		
		for(int studentId: studentIds) {
			List<List<Integer>> studentData = studentDAO.getStudentCourseData(studentId);
			studentData = sortByCoursePref(studentData);
			int count = 0;
			
			for(List<Integer> course: studentData) {
				int studentPerCourseCount = studentDAO.getCourseCount(course.get(0));
				if(count==4)
					break;
				if(studentPerCourseCount>=3 && studentPerCourseCount<=10)
				{
					count++;
					courseCount.getOrDefault(course.get(0),courseCount.getOrDefault(course.get(0),0)+1);
					adminDAO.setGradeCard(studentId,(int)course.get(0));
				}
				else if(studentPerCourseCount<3) {
					continue;
				}
				else if(studentPerCourseCount>10) {
					if(courseCount.getOrDefault(course.get(0),0)<10)
					{
						count++;
						courseCount.getOrDefault(course.get(0),courseCount.getOrDefault(course.get(0),0)+1);
						adminDAO.setGradeCard(studentId,(int)course.get(0));
					}
					else
						continue;
				}
//				if(studentPerCourseCount<3) {
//					dropCourse(course.get(0));
//					if(alternative_available(studentId))
//					{
//						studentDAO.listCourses();
//						studentDAO.registerCourse();
//					}
//					else {
//						notify.registrationCancel();
//						studentDAO.registerCourse();
//					}
//					/*
//					 * 1. Course Cancel
//					 * 		If alternatives available hai to usme se choose kar
//					 * 		else student ko notify kar ki uska registration cancel ho gaya hai firse register kar
//					 * */
//				}
//				else if(studentPerCourseCount>10) {
//					if(alternative_available(studentId))
//					{
//						studentDAO.listCourses();
//						studentDAO.registerCourse();
//					}
//					else {
//						notify.registrationCancel();
//						studentDAO.registerCourse();
//					}
//					/*
//					 *     If alternatives available hai to usme se choose kar
//					 * 		else student ko notify kar ki uska registration cancel ho gaya hai firse register kar
//					 * */
				//}
			if(count!=4)
				System.out.println("Not registered");
			else
				System.out.println("registered");
			}
		}
	}

	@Override
	public void addAdmin(Admin user) {
		// TODO Auto-generated method stub
		adminDAO.addAdmin(user);
	}

	@Override
	public void assignCoursesProf() {
		// TODO Auto-generated method stub
		//step1: get data of map having list of course and professor from professorcoursemapping
		//step2: get details of professor interested in that subject
		
		//step3: now according to FCFS assign professsor to subject
		//step4: update professor with particular courseID in respective tables(professorcourse mapping and coursecatalog)
		
		List<Integer> listOfCourses = adminDAO.getListOfCourses();
		
		for(int course:listOfCourses)
		{
			List<Integer> profCourseData = adminDAO.getProfCourseData(course);
			if(profCourseData.size()!=0) {
			adminDAO.setProfCourse(profCourseData.get(0), course);
			}
			else
			{
				System.out.println("No professor is assigned for the course id: " +course);
			}
		}
		
	}

	@Override
	public void adminRegistration(Admin user) {
		// TODO Auto-generated method stub
		adminOp.addAdmin(user);
	}

}
