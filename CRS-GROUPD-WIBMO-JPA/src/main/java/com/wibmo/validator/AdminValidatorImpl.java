package com.wibmo.validator;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wibmo.service.NotificationOperation;
import com.wibmo.service.NotificationOperationImpl;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.entity.ProfessorCourseMap;
import com.wibmo.repository.AdminDAO;
import com.wibmo.repository.AdminDAOImpl;
import com.wibmo.repository.AdminRepository;
import com.wibmo.repository.ProfessorCourseMappingRepository;
import com.wibmo.repository.ProfessorRepository;
import com.wibmo.repository.StudentDAOImpl;
import com.wibmo.repository.StudentRepository;
//import com.wibmo.constant.NotificationConstants;

/**
 * To validate admin details
 */
@Component
public class AdminValidatorImpl implements ValidatorInterface{


	@Autowired
	public StudentRepository studentRepository;
	@Autowired
	public AdminRepository adminRepository;
	@Autowired
	public ProfessorRepository professorRepository;
	
	@Autowired
	public ProfessorCourseMappingRepository professorCourseMappingRepository;
	
	@Autowired
	public NotificationOperation notification;
	
	@Override
	/**
	 * To validate admin using email
	 */
	public boolean emailValidator(String email) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
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
	
	public List<Boolean> courseRegistrationValidator() {
		
		List<Integer> studentIds = studentDAO.getStudentIds();
		Map<Integer,Integer> courseCount = new HashMap<>();
		List<Boolean> isSuccess = new ArrayList<>();
		
		for(int studentId: studentIds) {
			int isRegistered = studentDAO.isStudentRegistered(studentId);
			int isApproved = studentDAO.isCourseRegistrationApproved(studentId);
			if(isApproved>0)
			{
				continue;
			}
			if(isRegistered==0) {
				System.out.println("Student has not Registered till now");
				continue;
			}
			else
				System.out.println("Student has Registered Successfully");
			
			List<Object[]> data = studentDAO.getStudentCourseData(studentId);
			List<List<Integer>> studentData = new ArrayList<>();
			for(Object[] result: data)
			{
				List<Integer> courseData = new ArrayList<>();
				courseData.add((Integer)result[0]);
				courseData.add((Integer)result[1]);
				studentData.add(courseData);
			}
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
				
				notification.sendNotification(NotificationConstants.APPROVE_REGISTRATION_NOTIFICATION, studentId);
				
				notification.sendNotification(NotificationConstants.FEE_PAYMENT_NOTIFICATION, studentId);
				isSuccess.add(true);
			}
			else {
				adminDAO.setRejectionStatus(studentId);		
				notification.sendNotification(NotificationConstants.REJECT_REGISTRATION_NOTIFICATION, studentId);
				isSuccess.add(false);
			}
				
		}
		return isSuccess;
	}
	
	
	public void assignCourseValidator() {
		
		
		List<Integer> professors = adminDAO.getProfessorsIds();
		
		Set<Integer> set = new HashSet<>();
		for(int professor:professors) 
		{
			List<Integer> courses = adminDAO.getProfessorCourses(professor);
		
			for(int course:courses) 
			{
				if(!set.contains(course))
				{
					set.add(course);
					adminDAO.approveCourse(professor,course);
				}
				else
					System.out.println("Course already assigned to another professor");
			}
			
		}
	}
	
}
