package com.wibmo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wibmo.dto.AddCourseDto;
import com.wibmo.dto.GradeCardResponseDTO;
import com.wibmo.dto.RegisterUserDto;
import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.SemesterRegistration;
import com.wibmo.entity.Student;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.entity.User;
import com.wibmo.repository.*;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.DuplicateCourseEntryException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;

/**
 * Student Operation Implementation
 */
@Service
public class StudentOperationImpl implements StudentOperation {

	@Autowired
	public StudentRepository studentDao;
	@Autowired
	public CourseRepository course;

	@Autowired
	private CourseRepository courseDao;

	@Autowired
	private StudentCourseMappingRepository studCoMapRepo;

	@Autowired
	private GradeCardRepository gradeCardRepository;


	/**
	 * Student Register for course
	 * 
	 * @param studentId
	 */
	@Override
	public void registerCourses(int studentId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		// Logic will be implemented in ADMIN Panel
		if (studentDao.findById(studentId).isEmpty() == true) {
			throw new UserNotFoundException(studentId);
		}
		studentDao.registerCourses(studentId);
	}

	/**
	 * To add new course for registration
	 * 
	 * @param studCoMap
	 * @throws DuplicateCourseEntryException
	 */
	@Override
	@Transactional
	public void addCourses(int userId, AddCourseDto addCourseDto)
			throws CourseNotFoundException, CourseLimitExceededException, UserNotFoundException {
		if (studentDao.findById(userId).isEmpty() == true) {
			throw new UserNotFoundException(userId);
		}
		if (studentDao.getCourseCount(userId) > 6) {
			throw new CourseLimitExceededException();
		}
		for (Map.Entry<String, Integer> entry : addCourseDto.getCourses().entrySet()) {
			String courseId = entry.getKey();
			int pref = entry.getValue();
			if (courseDao.findByCourseId(courseId)==null) {
				throw new CourseNotFoundException(courseId);
			}
		}

		for (Map.Entry<String, Integer> entry : addCourseDto.getCourses().entrySet()) {
			StudentCourseMap studCoMapping = new StudentCourseMap();
			studCoMapping.setStudent(studentDao.findById(userId).get());
			studCoMapping.setCourse(courseDao.findByCourseId(entry.getKey()));
			studCoMapping.setCoursePref(entry.getValue());
			studCoMapping.setIsRegister(0);
			studCoMapRepo.save(studCoMapping);
		}

	}

	/**
	 * To drop course from registration list
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException
	 */
	@Override
	@Transactional
	public int dropCourses(int studentId, String courseId) throws CourseNotFoundException, UserNotFoundException {
		// TODO Auto-generated method stub

		if (studentDao.findById(studentId).isEmpty() == true) {
			throw new UserNotFoundException(studentId);
		}
		if (courseDao.findByCourseId(courseId)==null) {
			throw new CourseNotFoundException(courseId);
		}
		Integer isRegister = studentDao.findCoursePreference(studentId, courseId);
		studentDao.dropCourses(studentId, courseId);
		return isRegister;
	}

	/**
	 * To get a list of registered course.
	 * 
	 * @param studentId
	 * @return a map of registered courseId and their name.
	 * @throws UserNotApprovedException
	 */
	@Override
	public Map<String, String> listCourse(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		int isPresent = studentDao.isApproved(studentId);
		if (isPresent < 1)
			throw new UserNotApprovedException(studentId);

		List<Object[]> list = studentDao.listCourse(studentId);
		Map<String, String> courses = new HashMap<>();
		for (Object[] result : list) {
			courses.put((String) result[0], (String) result[1]);
		}
		return courses;

	}

	/**
	 * To view the list of offered courses
	 */
	@Override
	public List<CourseCatalog> viewCourseCatalog() {
		Iterable<CourseCatalog> courses = courseDao.findAll();
		List<CourseCatalog> list = new ArrayList<>();
		courses.forEach(course -> list.add(course));
		return list;
	}


	/**
	 * To view the Grade card of all the courses of a student.
	 * 
	 * @param studId
	 * @throws UserNotFoundException
	 */
	@Override
	public GradeCardResponseDTO viewReportCard(int studentId) throws UserNotApprovedException {
		// TODO Auto-generated method stub
		if (studentDao.isApproved(studentId) < 1) {
			throw new UserNotApprovedException(studentId);
		}
		GradeCardResponseDTO gradeCardResponseDTO = new GradeCardResponseDTO();
		gradeCardResponseDTO.setStudentId(studentId);
		List<GradeCard> gradeCard = gradeCardRepository.findByStudent(studentDao.findById(studentId).get());
		
		double cgpa = 0.0;
		Map<String, Integer> mapper = new HashMap<>();
		mapper.put("A",10);
		mapper.put("B",9);
		mapper.put("C",8);
		mapper.put("D",7);
		mapper.put("E",6);
		mapper.put("F",5);
		
		double cgpaSum=0;
		
		for (GradeCard grade : gradeCard) {
					cgpaSum+=mapper.get(grade.getGrade());
		}
		
		cgpa = cgpaSum/(gradeCard.size()*10);
		
		
		for (GradeCard grade : gradeCard) {
			gradeCardResponseDTO.addGradeDetails(grade.getCatalog().getCourseId(), grade.getCatalog().getCourseName(),
					grade.getGrade());
		}
		gradeCardResponseDTO.setCgpa(cgpa*10);
		return gradeCardResponseDTO;
	}

	/**
	 * To check if a student is registered of not.
	 * 
	 * @param userId
	 * @return True if student is registered else returns False.
	 */
	@Override
	public int isApproved(int userId) {
		// TODO Auto-generated method stub
		int flag = studentDao.isApproved(userId);
		return flag;
	}

	public boolean isRegistered(int userId) 
	{
		int count=studCoMapRepo.countByStudentAndIsRegister(studentDao.findById(userId).get(), 1);
		if(count>=4)
			return true;
		else
			return false;
			
	}
	/**
	 * To get the Student by Email
	 * 
	 * @param userEmail
	 * @return count of student with given email
	 */
	@Override
	public int getStudentByEmail(String userEmail) {
		int studentId = studentDao.findByEmail(userEmail);
		return studentId;
	}

	/**
	 * To get the list of courses
	 * 
	 * @param userId
	 * @return Map<Integer,String> contains courseId and courseName with userId
	 */
	@Override
	public Map<Integer, String> getAddedCourses(int userId) {
		// TODO Auto-generated method stub
		List<Object[]> list = studentDao.getAddedCourses(userId);
		Map<Integer, String> map = new HashMap<>();
		for (Object[] result : list)
			map.put((Integer) result[0], (String) result[1]);
		return map;
	}

	/**
	 * Check whether student is registered or not
	 * 
	 * @param userId
	 * @return 1 if student is registered
	 */
	@Override
	public Integer isStudentRegistered(int userId) {
		// TODO Auto-generated method stub
		Integer isRegistered = studentDao.isStudentRegistered(userId);
		return isRegistered;
	}

}
