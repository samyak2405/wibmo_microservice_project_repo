package com.wibmo.validator;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wibmo.business.NotificationOperation;
import com.wibmo.business.NotificationOperationImpl;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.dao.AdminDAO;
import com.wibmo.dao.AdminDAOImpl;
import com.wibmo.dao.StudentDAOImpl;
/**
 * To validate admin details
 */
public class AdminValidatorImpl implements ValidatorInterface{

	StudentDAOImpl studentDAO = StudentDAOImpl.getInstance();
	AdminDAO adminDAO = AdminDAOImpl.getInstance();
	public NotificationOperation notification=new NotificationOperationImpl();
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
	
	public void courseRegistrationValidator() {
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
	
	
	public void assignCourseValidator() {
		
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
	
}
