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
import com.wibmo.exception.UserAlreadyExistsException;
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
	public AdminRepository adminRepository;

	@Autowired
	public StudentRepository studentRepository;

	@Autowired
	public ProfessorRepository professorRepository;

	@Autowired
	public ProfessorCourseMappingRepository professorCourseMappingRepository;

	@Autowired
	public ValidatorInterface validate;
	@Autowired
	public NotificationOperation notification;

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
	 * To Add a new Admin
	 * 
	 * @param User contains Admin details
	 */
	@Override
	public Admin addAdmin(User user) {

		Admin admin = adminRepository.save((Admin) user);
		return admin;
	}

	/**
	 * To assign which course will be taught by which professor
	 */
	@Override
	public void assignCoursesProf() {
		List<Integer> professorsIds = professorCourseMappingRepository.listProfessorIds();
		Set<Integer> set = new HashSet<>();
		for (int professorId : professorsIds) {
			Professor professor = professorRepository.findById(professorId).get();
			List<Integer> coursesIds = professorCourseMappingRepository.getProfessorCourses(professorId);
			for (int courseId : coursesIds) {
				CourseCatalog course = courseRepository.findById(courseId).get();
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
	 * To approve registration of a new Admin
	 * 
	 * @param User contains admin details
	 */
	@Override
	public void adminRegistration(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		if (validate.emailValidator(user.getUserEmail())) {
			if (adminRepository.findById(user.getUserId()).isEmpty() == false) {
				throw new UserAlreadyExistsException(user.getUserEmail());
			}
			Admin admin = new Admin();
			admin.setUserEmail(user.getUserEmail());
			admin.setUserId(user.getUserId());
			admin.setUserName(user.getUserName());
			admin.setUserPassword(user.getUserPassword());
			admin.setUserPhonenumber(admin.getUserPhonenumber());
			admin.setIsApproved(0);
			adminRepository.save(admin);
		} else
			log.info("Invalid Email Id");
	}

	/**
	 * To Approve Student Course Registration
	 * 
	 * @return List<Boolean> contains true if courses are assigned successfully to
	 *         student otherwise false
	 */
	@Override
	public Map<Integer,Boolean> approveCourseRegistration() {
		// TODO Auto-generated method stub
		Map<Integer,Boolean> isSuccess = validate.courseRegistrationValidator();
		return isSuccess;
	}

	/**
	 * To approve a specific student
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
	 * To get Admin by email
	 * 
	 * @param userEmail
	 * @return Admin
	 */
	@Override
	public int getAdminByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return adminRepository.getAdminById(userEmail);
	}

}
