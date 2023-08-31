package com.wibmo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.dto.RegisterUserDto;
import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.Professor;
import com.wibmo.entity.ProfessorCourseMap;
import com.wibmo.entity.Student;
import com.wibmo.entity.StudentCourseMap;
import com.wibmo.entity.User;
import com.wibmo.repository.*;
import com.wibmo.exception.*;

/**
 * Professor Service Implementation
 */
@Service
public class ProfessorOperationImpl implements ProfessorOperation {

	@Autowired
	ProfessorRepository professorDao;
	@Autowired
	StudentRepository studentDao;
	@Autowired
	CourseRepository courseDao;
	@Autowired
	GradeCardRepository gradeCardRepository;

	@Autowired
	private StudentCourseMappingRepository studentCourseRepo;

	@Autowired
	private ProfessorCourseMappingRepository professorCoMapRepo;

	/**
	 * To set the grades of a student
	 * 
	 * @param studentId
	 * @param courseId
	 * @param grade
	 * @throws UserNotFoundException
	 * @throws CourseNotFoundException
	 */
	@Override
	@Transactional
	public void setGrades(int professorId, int studentId, String courseId, String grade)
			throws UserNotFoundException, CourseNotFoundException, CourseNotAssignedException {

		if (courseDao.findByCourseId(courseId) == null) {
			throw new CourseNotFoundException(courseId);
		} else if (studentDao.findById(studentId) == null) {
			throw new UserNotFoundException(studentId);
		}
		ProfessorCourseMap professorCourseMap = professorCoMapRepo.findByProfessorAndCourseCatalog(
				professorDao.findById(professorId).get(), courseDao.findByCourseId(courseId));
		if ((professorCourseMap == null)
				|| ((professorCourseMap != null) && (professorCourseMap.getIsApproved() == 0))) {
			throw new CourseNotAssignedException(courseId);
		}
		GradeCard gradeCard = gradeCardRepository.findByStudentAndCatalog(studentDao.findById(studentId).get(),
				courseDao.findByCourseId(courseId));
		gradeCard.setGrade(grade);
		gradeCardRepository.save(gradeCard);
	}

	/**
	 * To request to teach a specific course
	 * 
	 * @param professorid
	 * @param courseIdList
	 * @throws CourseNotFoundException
	 */
	@Override
	@Transactional
	public void requestCourseOffering(int professorid, List<String> courseIdList)
			throws CourseNotFoundException, UserNotFoundException {

		// TODO Auto-generated method stub
		if (professorDao.findById(professorid).isEmpty() == true) {
			throw new UserNotFoundException(professorid);
		}
		for (String courseId : courseIdList) {
			if (courseDao.findByCourseId(courseId) == null)
				throw new CourseNotFoundException(courseId);
		}

		courseIdList.forEach((courseId) -> {
			ProfessorCourseMap profCoMap = new ProfessorCourseMap();
			profCoMap.setProfessor(professorDao.findById(professorid).get());
			profCoMap.setCourseCatalog(courseDao.findByCourseId(courseId));
			profCoMap.setIsApproved(0);
			professorCoMapRepo.save(profCoMap);
			System.out.println("Done");
		});
		return;
	}

	/**
	 * To view list of registered student in a specific course.
	 * 
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return List<Student> for particular course with courseId under professor
	 *         with professorId
	 */
	@Override
	public List<Student> viewStudentList(int professorId, String courseId)
			throws CourseNotFoundException, CourseNotAssignedException {

		if (courseDao.findByCourseId(courseId) == null) {
			throw new CourseNotFoundException(courseId);
		}
		ProfessorCourseMap professorCourseMap = professorCoMapRepo.findByProfessorAndCourseCatalog(
				professorDao.findById(professorId).get(), courseDao.findByCourseId(courseId));
		if ((professorCourseMap == null)
				|| ((professorCourseMap != null) && (professorCourseMap.getIsApproved() == 0))) {
			throw new CourseNotAssignedException(courseId);
		}
		List<StudentCourseMap> studentCo = studentCourseRepo.findByCourse(courseDao.findByCourseId(courseId));

		List<Student> students = studentCo.stream().map(studentMap -> new Student(studentMap.getStudent()))
				.collect(Collectors.toList());
		return students;
	}

	/**
	 * To view the list of all the courses
	 * 
	 * @return List<CourseCatalog> containing list of courses
	 */
	@Override
	public List<CourseCatalog> viewCourseCatalog() {
		Iterable<CourseCatalog> courses = courseDao.findAll();
		List<CourseCatalog> list = new ArrayList<>();
		courses.forEach(list::add);
		return list;
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
	 * Find professor using Email
	 * 
	 * @param userEmail
	 * @return count with get professor by id
	 */
	@Override
	public int getProfessorById(String userEmail) {
		// TODO Auto-generated method stub
		return professorDao.getProfessorById(userEmail);

	}

	/**
	 * Get the list of Assigned courses to professor
	 * 
	 * @param userId
	 * @return Map<Integer,String> containing CourseId and CourseName
	 * @throws UserNotFoundException
	 */
	@Override
	public Map<Integer, String> listOfApprovedCourses(int userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if (professorDao.findById(userId).isEmpty() == true) {
			throw new UserNotFoundException(userId);
		}
		List<Object[]> list = professorDao.listOfApprovedCourses(userId);
		Map<Integer, String> courses = new HashMap<>();
		for (Object[] result : list)
			courses.put((Integer) result[0], (String) result[1]);
		return courses;
	}

/**
 * returns list of professors
 * 	@return list of professors
 */
	@Override
	public List<Professor> viewProfessor() {
		Iterable<Professor> professors = professorDao.findAll();
		List<Professor> list = new ArrayList<>();
		professors.forEach(professor->
			list.add(professor));
		return list;
	}

}
