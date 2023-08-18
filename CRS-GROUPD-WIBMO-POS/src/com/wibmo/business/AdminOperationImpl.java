package com.wibmo.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wibmo.bean.*;

import com.wibmo.dao.*;
import com.wibmo.validator.*;

public class AdminOperationImpl implements AdminOperation{

	Scanner scan = new Scanner(System.in);
	static AdminOperationImpl adminOp = new AdminOperationImpl();
	AdminDAO adminDAO = AdminDAOImpl.getInstance();
	StudentDAOImpl studentDAO = StudentDAOImpl.getInstance();
	public ValidatorInterface validate = new AdminValidatorImpl();
	
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
		adminDAO.setApprovedStudents();
	}

	@Override
	public void addAdmin(Admin user) {
		// TODO Auto-generated method stub
		adminDAO.addAdmin(user);
	}

	@Override
	public void assignCoursesProf() {
		
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
		if(validate.emailValidator(user.getUserEmail()))
			adminOp.addAdmin(user);
		else
			System.out.println("Invalid Email Id");
	}

	@Override
	public void approveCourseRegistration() {
		
		// TODO Auto-generated method stub
				List<Integer> studentIds = studentDAO.getStudentIds();
				Map<Integer,Integer> courseCount = new HashMap<>();
				
				for(int studentId: studentIds) {
					List<List<Integer>> studentData = studentDAO.getStudentCourseData(studentId);
					studentData = sortByCoursePref(studentData);
					int count = 0;
					
					for(List<Integer> course: studentData) {
						int studentPerCourseCount = studentDAO.getStudentCourseCount(course.get(0));
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

					if(count!=4)
						System.out.println("Student Course Registration Unsuccessful");
					else
						//notification
						System.out.println("Student Course Registration Successful");
					}
				}
	}

	@Override
	public void approveStudentById() {
		// TODO Auto-generated method stub
		List<Integer> studentIds = adminDAO.pendingRegistration();
		System.out.println("Choose from below given student ids");
		studentIds.forEach(studentId->System.out.println(String.format("%20s\n", studentId)));
		System.out.println("Enter the StudentId: ");
		int studentId = scan.nextInt();
		adminDAO.setApprovedStudentById(studentId);
	}

}
