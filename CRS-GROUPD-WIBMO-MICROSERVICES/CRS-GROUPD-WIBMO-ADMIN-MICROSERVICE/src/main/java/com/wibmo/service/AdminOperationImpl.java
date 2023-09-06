package com.wibmo.service;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.wibmo.entity.*;
import com.wibmo.repository.*;
import com.wibmo.exception.UserAlreadyApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.*;

/**
 * Admin Operations Implementation
 */
@Service
public class AdminOperationImpl implements AdminOperation {

	private Logger log = LogManager.getLogger();

	Scanner scan = new Scanner(System.in);

	@Autowired
	public StudentRepository studentRepository;
	@Autowired
	public AdminRepository adminRepository;

	@Autowired
	public ProfessorRepository professorRepository;

	@Autowired
	public ProfessorCourseMappingRepository professorCourseMappingRepository;

	@Autowired
	public ValidatorInterface validate;
//	@Autowired
//	public NotificationOperation notification;

	@Autowired
	public CourseRepository courseRepository;

	/**
	 * To Approve Student Registration
	 */
	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		adminRepository.approveStudentRegistration();
	}

	/**
	 * To approve a specific student by studentId
	 * 
	 * @param studentId
	 */
	@Override
	public void approveStudentById(int id) throws UserNotFoundException {

		if (studentRepository.findById(id).isEmpty() == true) {
			throw new UserNotFoundException(id);
		}
		studentRepository.setApprovedStudentById(id);
	}

	/**
	 * To Approve Student Course Registration
	 * 
	 * @return List<Boolean> contains true if courses are assigned successfully to
	 *         student otherwise false
	 */
	@Override
	public Map<Integer, Boolean> approveCourseRegistration() {
		// TODO Auto-generated method stub
		Map<Integer, Boolean> isSuccess = validate.courseRegistrationValidator();
		return isSuccess;
	}

	/**
	 * To assign which course will be taught by which professor
	 */
	@Override
	public void assignCoursesProf() {
		List<Integer> professorsIds = professorCourseMappingRepository.listProfessorIds();
		Set<String> set = new HashSet<>();
		for (int professorId : professorsIds) {
			Professor professor = professorRepository.findById(professorId).get();
			List<String> coursesIds = professorCourseMappingRepository.getProfessorCourses(professorId);
			for (String courseId : coursesIds) {
				CourseCatalog course = courseRepository.findByCourseId(courseId);
				if (!set.contains(courseId)) {
					set.add(courseId);
					ProfessorCourseMap professorCourseMap = professorCourseMappingRepository
							.findByProfessorAndCourseCatalog(professor, course);
					professorCourseMap.setIsApproved(1);
					professorCourseMappingRepository.save(professorCourseMap);
				} else
					System.out.println("Course already assigned to another professor");
			}
		}
	}

	/**
	 * To Add a new Admin
	 * 
	 * @param User contains Admin details
	 */
	@Override
	public void approveAdmin(int userId) throws UserNotFoundException, UserAlreadyApprovedException {

		if (adminRepository.findById(userId).isEmpty() == true) {
			throw new UserNotFoundException(userId);
		}
		if (adminRepository.countByUserIdAndIsApproved(userId, 1) > 0) {
			throw new UserAlreadyApprovedException(userId);
		}
		adminRepository.setAdminApproval(userId);

	}

	public int getAdminByEmail(String string) {
		// TODO Auto-generated method stub
		return 0;
	}
}
