package com.wibmo.service;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.wibmo.entity.*;
import com.wibmo.repository.*;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.validator.*;


@Service
public class AdminOperationImpl implements AdminOperation{
	
	private Logger log=LogManager.getLogger();
	
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
	

	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		adminRepository.approveStudentRegistration();
	}

	
	
	
	@Override
	public void addAdmin(User user) {
		adminRepository.save((Admin) user);
	}

	
	
	@Override
	public void assignCoursesProf() 
	{
		List<Integer> professorsIds = professorCourseMappingRepository.listProfessorIds();
		Set<Integer> set = new HashSet<>();
		for(int professorId:professorsIds) 
		{
			Professor professor=professorRepository.findById(professorId).get();
			List<Integer> coursesIds = professorCourseMappingRepository.getProfessorCourses(professorId);
			for(int courseId:coursesIds) 
			{
				CourseCatalog course=courseRepository.findById(courseId).get();
				if(!set.contains(courseId))
				{
					ProfessorCourseMap professorCourseMap=professorCourseMappingRepository.findByProfessorAndCourseCatalog(professor, course);
					professorCourseMap.setIsApproved(1);
					professorCourseMappingRepository.save(professorCourseMap);
				}
				else
					System.out.println("Course already assigned to another professor");
				}
		}
	}
	
	
	
	@Override
	public void adminRegistration(User user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub
		if(validate.emailValidator(user.getUserEmail()))
			{
			if(adminRepository.findById(user.getUserId()).isEmpty()==false)
			{
				throw new UserAlreadyExistsException(user.getUserEmail());
			}
			Admin admin=new Admin();
			admin.setUserEmail(user.getUserEmail());
			admin.setUserId(user.getUserId());
			admin.setUserName(user.getUserName());
			admin.setUserPassword(user.getUserPassword());
			admin.setUserPhonenumber(admin.getUserPhonenumber());
			admin.setIsApproved(0);
			adminRepository.save(admin);
			}
		else
			//System.out.println("Invalid Email Id");
			log.info("Invalid Email Id");
	}

	@Override
	public List<Boolean> approveCourseRegistration() {
		// TODO Auto-generated method stub
		List<Boolean> isSuccess = validate.courseRegistrationValidator();
		return isSuccess;
	}

	@Override
	public void approveStudentById(int id) throws UserNotFoundException{

		if(studentRepository.findById(id).isEmpty()==true) {
			throw new UserNotFoundException(id);
		}
		studentRepository.setApprovedStudentById(id);
	}

	@Override
	public int getAdminById(String userEmail) {
		// TODO Auto-generated method stub
		return adminRepository.getAdminById(userEmail);
	}

	

	

}
