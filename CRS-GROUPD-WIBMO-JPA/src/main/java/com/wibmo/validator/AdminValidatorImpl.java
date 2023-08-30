package com.wibmo.validator;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wibmo.service.NotificationOperation;
import com.wibmo.constant.NotificationConstants;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.NotificationStudentMapping;
import com.wibmo.repository.AdminRepository;
import com.wibmo.repository.CourseRepository;
import com.wibmo.repository.GradeCardRepository;
import com.wibmo.repository.NotificationRepository;
import com.wibmo.repository.NotificationStudentMappingRepository;
import com.wibmo.repository.ProfessorCourseMappingRepository;
import com.wibmo.repository.ProfessorRepository;
import com.wibmo.repository.StudentRepository;

/**
 * To validate admin details
 */
@Component
public class AdminValidatorImpl implements ValidatorInterface {

	@Autowired
	public StudentRepository studentRepository;
	@Autowired
	public AdminRepository adminRepository;
	@Autowired
	public ProfessorRepository professorRepository;

	@Autowired
	public ProfessorCourseMappingRepository professorCourseMappingRepository;


	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private GradeCardRepository gradeRepository;


	@Override
	/**
	 * @param email
	 * @return true if Email is in correct format otherwise false
	 */
	public boolean emailValidator(String email) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Sort the list in order of coure preference
	 * 
	 * @param List<List<Integer>> contains courses with courseId and
	 *                            coursePreference
	 * @return sorted List<List<Integer>>
	 */
	public List<List<Integer>> sortByCoursePref(List<List<Integer>> list) {
		Collections.sort(list, new Comparator<List<Integer>>() {
			public int compare(List<Integer> a, List<Integer> b) {
				return a.get(1) - b.get(1);
			}
		});
		return list;
	}

	/**
	 * Algorithm for assigning courses to students
	 * 
	 * @return Map<Integer,Boolean> which contains studentId and their respective
	 *         status of registration
	 */
	public Map<Integer, Boolean> courseRegistrationValidator() {

		List<Integer> studentIds = studentRepository.getStudentIds();

		Map<Integer, Integer> courseCount = new HashMap<>();
		Map<Integer, Boolean> isSuccess = new HashMap<>();

		for (int studentId : studentIds) {
			int isRegistered = studentRepository.isStudentRegistered(studentId);
			int isApproved = studentRepository.isCourseRegistrationApproved(studentId);
			if (isApproved > 0) {
				continue;
			}
			if (isRegistered == 0) {
				System.out.println("Student has not Registered till now");
				continue;
			} else
				System.out.println("Student has Registered Successfully");

			List<Object[]> data = studentRepository.getStudentCourseData(studentId);
			List<List<Integer>> studentData = new ArrayList<>();
			for (Object[] result : data) {
				List<Integer> courseData = new ArrayList<>();
				courseData.add((Integer) result[0]);
				courseData.add((Integer) result[1]);
				studentData.add(courseData);
			}
			studentData = sortByCoursePref(studentData);
			int count = 0;

			for (List<Integer> course : studentData) {
				int studentPerCourseCount = studentRepository.getStudentCourseCount(course.get(0));
				if (count == 4)
					break;
				if (studentPerCourseCount >= 3 && studentPerCourseCount <= 10) {
					count++;
					courseCount.getOrDefault(course.get(0), courseCount.getOrDefault(course.get(0), 0) + 1);
					GradeCard gradeCard = new GradeCard();
					gradeCard.setStudent(studentRepository.findById(studentId).get());
					gradeCard.setCatalog(courseRepository.findById(course.get(0)).get());
					gradeCard.setGrade("NA");
					gradeRepository.save(gradeCard);
				} else if (studentPerCourseCount < 3) {
					continue;
				} else if (studentPerCourseCount > 10) {
					if (courseCount.getOrDefault(course.get(0), 0) < 10) {
						count++;
						courseCount.getOrDefault(course.get(0), courseCount.getOrDefault(course.get(0), 0) + 1);
						courseCount.getOrDefault(course.get(0), courseCount.getOrDefault(course.get(0), 0) + 1);
						GradeCard gradeCard = new GradeCard();
						gradeCard.setStudent(studentRepository.findById(studentId).get());
						gradeCard.setCatalog(courseRepository.findById(course.get(0)).get());
						gradeCard.setGrade("NA");
						gradeRepository.save(gradeCard);
					} else
						continue;
				}
			}
			if (count == 4) {

//				notificationStudentMappingRepository.save(new NotificationStudentMapping(
//						studentRepository.findById(studentId).get(), notificationRepository
//								.findById(NotificationConstants.APPROVE_REGISTRATION_NOTIFICATION).get()));
//
//				notificationStudentMappingRepository
//						.save(new NotificationStudentMapping(studentRepository.findById(studentId).get(),
//								notificationRepository.findById(NotificationConstants.FEE_PAYMENT_NOTIFICATION).get()));

				isSuccess.put(studentId, true);
			} else {

//				notificationStudentMappingRepository.save(new NotificationStudentMapping(
//						studentRepository.findById(studentId).get(),
//						notificationRepository.findById(NotificationConstants.REJECT_REGISTRATION_NOTIFICATION).get()));
				isSuccess.put(studentId, false);
			}

		}
		return isSuccess;
	}

}
