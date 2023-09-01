/**
 * 
 */
package com.wibmo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.wibmo.dto.RegisterUserDto;
import com.wibmo.entity.*;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.repository.*;
import com.wibmo.validator.ValidatorInterface;

/**
 * Authenticating Users Implementation
 */
@Service
@Transactional
public class AuthenticationOperationImpl implements AuthenticationOperation {
	@Autowired
	AuthenticationRepository authenticate;

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	private SemesterRegistrationRepository semesterRegistrationRepository;
	@Autowired
	ProfessorRepository professorDao;
	@Autowired
	public AdminRepository adminRepository;
	@Autowired
	public ValidatorInterface validate;


	/*
	 * Check whether User with details can log into system or not
	 * 
	 * @param User Email
	 * 
	 * @param User Password
	 * 
	 * @param User Role
	 * 
	 * @return true if user details matched otherwise return false
	 */
	@Override
	public boolean loggedin(String userEmail, String userPassword, int role, StringBuilder msg) {
		// TODO Auto-generated method stub

		User user = null;
		if (role == 1) {

			user = authenticate.studentLoggedin(userEmail);

		} else if (role == 2) {
			user = authenticate.professorLoggedin(userEmail);

		}

		else if (role == 3) {
			user = authenticate.adminLoggedin(userEmail);
		}
		if (user == null) {
			msg.append("User Not Found");
			return false;
		}
		if (userPassword.equalsIgnoreCase(user.getUserPassword())) {

			if (role == 1) {

				if (((Student) user).getIsapproved() == 1) {
					msg.append("Login Successful");
					return true;
				} else {
					msg.append("Registration Not Approved");
					return false;
				}

			} 
			else if(role==3) {
				if (((Admin) user).getIsApproved() == 1) {
					msg.append("Login Successful");
					return true;
				} else {
					msg.append("Registration Not Approved");
					return false;
				}
			}
			else {

				msg.append("Login Successful");
				return true;
			}

		} else {
			msg.append("Invalid Credentials");
			return false;
		}

	}

	/*
	 * Update User Password
	 * 
	 * @param User Email
	 * 
	 * @param User Password
	 * 
	 * @param User Role
	 */
	@Override
	public void updatePassword(String userEmail, String userPassword, int role) {
		// TODO Auto-generated method stub

		if (role == 1) {

			authenticate.updateStudentPassword(userEmail, userPassword);

		} else if (role == 2) {
			authenticate.updateProfessorPassword(userEmail, userPassword);
		} else if (role == 3) {
			authenticate.updateAdminPassword(userEmail, userPassword);
		}
	}
	
	

	/**
	 * To register a new Student user.
	 * 
	 * @param user
	 * @throws StudentAlreadyRegisteredException
	 */
	@Override
	public void registerStudent(RegisterUserDto user) throws StudentAlreadyRegisteredException {
		// TODO Auto-generated method stub
		Student student = new Student();
		SemesterRegistration semesterRegistration=new SemesterRegistration();

		if (studentRepo.findByEmail(user.getUserEmail()) > 0) {
			throw new StudentAlreadyRegisteredException(user.getUserEmail());
		}
		student.setUserName(user.getUserName());
		student.setUserEmail(user.getUserEmail());
		student.setUserPhonenumber(user.getUserPhonenumber());
		student.setUserPassword(user.getUserPassword());
		student.setUserId(user.getUserId());
		
		semesterRegistration.setStudent(student);
		semesterRegistration.setBranch(user.getBranch());
		semesterRegistration.setSemester(user.getSemester());
		semesterRegistration.setStudentName(user.getUserName());
		student.setSemesterRegistration(semesterRegistration);
		studentRepo.save(student);
		semesterRegistrationRepository.save(semesterRegistration);
		
	}
	
	
	/**
	 * For signing up a new professor
	 * 
	 * @param user
	 * @throws UserAlreadyExistsException
	 */
	@Override
	@Transactional
	public void registerProfessor(RegisterUserDto user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		Professor professor = new Professor();
		if (professorDao.findProfessorByEmail(user.getUserEmail()) > 0) {
			throw new UserAlreadyExistsException(user.getUserEmail());
		}
		System.out.println("123");
		professor.setUserId(user.getUserId());
		professor.setUserName(user.getUserName());
		professor.setUserEmail(user.getUserEmail());
		professor.setUserPhonenumber(user.getUserPhonenumber());
		professor.setUserPassword(user.getUserPassword());
		professorDao.save(professor);

	}

	/**
	 * To approve registration of a new Admin
	 * 
	 * @param User contains admin details
	 */
	@Override
	public void adminRegistration(RegisterUserDto user) throws UserAlreadyExistsException {
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
			admin.setUserPhonenumber(user.getUserPhonenumber());
			admin.setIsApproved(0);
			adminRepository.save(admin);
		}
	}

}
