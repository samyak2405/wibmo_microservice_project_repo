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
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.NotificationStudentMapping;
import com.wibmo.entity.ProfessorCourseMap;

import com.wibmo.repository.AdminRepository;
import com.wibmo.repository.CourseRepository;
import com.wibmo.repository.GradeCardRepository;
import com.wibmo.repository.NotificationRepository;
import com.wibmo.repository.NotificationStudentMappingRepository;
import com.wibmo.repository.ProfessorCourseMappingRepository;
import com.wibmo.repository.ProfessorRepository;
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
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private GradeCardRepository gradeRepository;
	
	@Autowired
	private NotificationStudentMappingRepository notificationStudentMappingRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
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
		
		List<Integer> studentIds = studentRepository.getStudentIds();
		
		Map<Integer,Integer> courseCount = new HashMap<>();
		List<Boolean> isSuccess = new ArrayList<>();
		
		for(int studentId: studentIds) {
			int isRegistered = studentRepository.isStudentRegistered(studentId);
			int isApproved = studentRepository.isCourseRegistrationApproved(studentId);
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
			
			List<Object[]> data = studentRepository.getStudentCourseData(studentId);
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
				int studentPerCourseCount = studentRepository.getStudentCourseCount(course.get(0));
				if(count==4)
					break;
				if(studentPerCourseCount>=3 && studentPerCourseCount<=10)
				{
					count++;
					courseCount.getOrDefault(course.get(0),courseCount.getOrDefault(course.get(0),0)+1);
					GradeCard gradeCard = new GradeCard();
					gradeCard.setStudent(studentRepository.findById(studentId).get());
					gradeCard.setCatalog(courseRepository.findById(course.get(0)).get());
					gradeCard.setGrade("NA");
					gradeRepository.save(gradeCard);
				}
				else if(studentPerCourseCount<3) {
					continue;
				}
				else if(studentPerCourseCount>10) {
					if(courseCount.getOrDefault(course.get(0),0)<10)
					{
						count++;
						courseCount.getOrDefault(course.get(0),courseCount.getOrDefault(course.get(0),0)+1);
						courseCount.getOrDefault(course.get(0),courseCount.getOrDefault(course.get(0),0)+1);
						GradeCard gradeCard = new GradeCard();
						gradeCard.setStudent(studentRepository.findById(studentId).get());
						gradeCard.setCatalog(courseRepository.findById(course.get(0)).get());
						gradeCard.setGrade("NA");
						gradeRepository.save(gradeCard);
					}
					else
						continue;
				}
			}
			if(count==4)
			{
			
				notificationStudentMappingRepository.save(new NotificationStudentMapping( studentRepository.findById(studentId).get(),
						notificationRepository.findById(NotificationConstants.APPROVE_REGISTRATION_NOTIFICATION).get()));
				
				notificationStudentMappingRepository.save(new NotificationStudentMapping(studentRepository.findById(studentId).get(),
						notificationRepository.findById(NotificationConstants.FEE_PAYMENT_NOTIFICATION).get()));

//				notification.sendNotification(NotificationConstants.APPROVE_REGISTRATION_NOTIFICATION, studentId);
//				
//				notification.sendNotification(NotificationConstants.FEE_PAYMENT_NOTIFICATION, studentId);
				isSuccess.add(true);
			}
			else {
				
				notificationStudentMappingRepository.save(new NotificationStudentMapping( studentRepository.findById(studentId).get(),
						notificationRepository.findById(NotificationConstants.REJECT_REGISTRATION_NOTIFICATION).get()));

//				adminRepository.setRejectionStatus(studentId);		
//				notification.sendNotification(NotificationConstants.REJECT_REGISTRATION_NOTIFICATION, studentId);
				isSuccess.add(false);
			}
				
		}
		return isSuccess;
	}
	
	
	
	
}
