package com.wibmo.validator;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.wibmo.constant.NotificationConstants;
import com.wibmo.dto.NotificationDto;
import com.wibmo.entity.GradeCard;
import com.wibmo.repository.AdminRepository;
import com.wibmo.repository.CourseRepository;
import com.wibmo.repository.GradeCardRepository;
import com.wibmo.repository.NotificationRepository;
import com.wibmo.repository.StudentRepository;

/**
 * To validate admin details
 */
@Component
public class AdminValidatorImpl implements ValidatorInterface {

	private static final String TOPIC = "student";
	
	private static final Logger log = LoggerFactory.getLogger(AdminValidatorImpl.class);
	@Autowired
	public StudentRepository studentRepository;
	@Autowired
	public AdminRepository adminRepository;
	@Autowired
	public NotificationRepository notificationRepo;
//
//	@Autowired
//	public ProfessorCourseMappingRepository professorCourseMappingRepository;

	@Autowired
	private KafkaTemplate<String,NotificationDto> kafkaTemplate;
	
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


	/**
	 * Algorithm for assigning courses to students
	 * @param studentId 
	 * 
	 * @return Map<Integer,Boolean> which contains studentId and their respective
	 *         status of registration
	 */
	public Map<Integer, Boolean> courseRegistrationValidator() {

		List<Integer> studentIds = studentRepository.getStudentIds();
		System.out.println("Hello world");
		Map<String, Integer> courseCount = new HashMap<>();
		Map<Integer, Boolean> isSuccess = new HashMap<>();

		for(int studentId:studentIds)
		{
			System.out.println(studentId);
			int isRegistered = studentRepository.isStudentRegistered(studentId);
			System.out.println(isRegistered);
			int isApproved = studentRepository.isCourseRegistrationApproved(studentId);
			System.out.println(studentId);
			int feePaymentStatus=studentRepository.findById(studentId).get().getCourseRegistrationStatus();
			if (isApproved > 0) {
				log.info("Student with id {} not approved",studentId);
				continue;
			}
			if(feePaymentStatus==0)
			{
				log.info("Payment not done.");
				continue;
			}
			
			if (isRegistered == 0) {
				log.info("Student has not Registered till now");
				continue;
			} else
				log.info("Student has Registered Successfully");

			List<Object[]> data = studentRepository.getStudentCourseData(studentId);
			Map<String,Integer> studentData = new HashMap<>();
			for (Object[] result : data) 
				studentData.put((String)result[0], (Integer)result[1]);
			studentData = studentData.entrySet()
					  .stream()
					  .sorted(Map.Entry.comparingByValue())
					  .collect(Collectors.toMap(
					    Map.Entry::getKey, 
					    Map.Entry::getValue, 
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			
			int count = 0;

			for (Entry<String,Integer>course : studentData.entrySet()) {
				
				int studentPerCourseCount = studentRepository.getStudentCourseCount(course.getKey());
				if (count == 4)
					break;
				if (studentPerCourseCount >= 3 && studentPerCourseCount <= 10) {
					count++;
					courseCount.getOrDefault(course.getKey(), courseCount.getOrDefault(course.getKey(), 0) + 1);
					GradeCard gradeCard = new GradeCard();
					gradeCard.setStudent(studentRepository.findById(studentId).get());
					gradeCard.setCatalog(courseRepository.findByCourseId(course.getKey()));
					gradeCard.setGrade("NA");
					gradeRepository.save(gradeCard);
				} else if (studentPerCourseCount < 3) {
					continue;
				} else if (studentPerCourseCount > 10) {
					if (courseCount.getOrDefault(course.getKey(), 0) < 10) {
						count++;
						courseCount.getOrDefault(course.getKey(), courseCount.getOrDefault(course.getKey(), 0) + 1);
						courseCount.getOrDefault(course.getKey(), courseCount.getOrDefault(course.getKey(), 0) + 1);
						GradeCard gradeCard = new GradeCard();
						gradeCard.setStudent(studentRepository.findById(studentId).get());
						gradeCard.setCatalog(courseRepository.findByCourseId(course.getKey()));
						gradeCard.setGrade("NA");
						gradeRepository.save(gradeCard);
					} else
						continue;
				}
			}
			if (count == 4) {
				
				kafkaTemplate.send(TOPIC, new NotificationDto(studentId,"Course Registration Approved"));
				isSuccess.put(studentId, true);
			} else {				
				kafkaTemplate.send(TOPIC, new NotificationDto(studentId,"Course Registration Rejected"));
			isSuccess.put(studentId, false);
			}

		}
		return isSuccess;
	}

}
