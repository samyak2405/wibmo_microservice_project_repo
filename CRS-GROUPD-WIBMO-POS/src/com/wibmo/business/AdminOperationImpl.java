package com.wibmo.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wibmo.bean.*;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.dao.*;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.*;

public class AdminOperationImpl implements AdminOperation{

	Scanner scan = new Scanner(System.in);
	static AdminOperationImpl adminOp = new AdminOperationImpl();
	AdminDAO adminDAO = AdminDAOImpl.getInstance();
	StudentDAOImpl studentDAO = StudentDAOImpl.getInstance();
	public ValidatorInterface validate = new AdminValidatorImpl();
	public NotificationOperation notification=new NotificationOperationImpl();
	
	
	
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
	public void adminRegistration(Admin user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		if(validate.emailValidator(user.getUserEmail()))
			{
			if(adminDAO.searchAdmin(user.getUserId()))
			{
				throw new UserAlreadyExistsException(user.getUserId());
			}
			adminOp.addAdmin(user);
			
			}
		else
			System.out.println("Invalid Email Id");
	}

	@Override
	public void approveCourseRegistration() {
		// TODO Auto-generated method stub
				List<Integer> studentIds = studentDAO.getStudentIds();
				Map<Integer,Integer> courseCount = new HashMap<>();
				
				for(int studentId: studentIds) {
					int isRegistered = studentDAO.isStudentRegistered(studentId);
					int isApproved = studentDAO.isRegistrationApproved(studentId);
					if(isApproved==studentId)
					{
						continue;
					}
					if(isRegistered==0) {
						System.out.println("Student has not Registered till now");
						continue;
					}
					else
						System.out.println("Student has Registered Successfully");
					
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
					}
					if(count==4)
					{
						System.out.println("Student Course Registration Successful");
						notification.sendNotification(NotificationConstants.APPROVE_REGISTRATION_NOTIFICATION, studentId);
						notification.sendNotification(NotificationConstants.FEE_PAYMENT_NOTIFICATION, studentId);						
					}
			
					else {
						
						System.out.println("Student Course Registration UnSuccessful");
						notification.sendNotification(NotificationConstants.REJECT_REGISTRATION_NOTIFICATION, studentId);
						
					}
				}
	}

	@Override
	public void approveStudentById() throws UserNotFoundException{
		// TODO Auto-generated method stub
		List<Integer> studentIds = adminDAO.pendingRegistration();
		System.out.println();

        System.out.println("===================================================================================");
		System.out.println("Choose from below given student ids");
		studentIds.forEach(studentId->System.out.println(String.format("%20s\n", studentId)));
		System.out.println();

        System.out.println("===================================================================================");
		System.out.println("Enter the StudentId: ");
		int studentId = scan.nextInt();
		if(studentDAO.searchStudent(studentId)==false) {
			throw new UserNotFoundException(studentId);
		}
		adminDAO.setApprovedStudentById(studentId);
	}

}
