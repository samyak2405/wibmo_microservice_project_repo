/**
 * 
 */
package com.wibmo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.Professor;
import com.wibmo.exception.CourseNotAssignedException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.ProfessorOperationImpl;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
public class TestProfessorDAO {
	
	@InjectMocks
	ProfessorOperationImpl professorOp;
	
	@Mock
	ProfessorRepository professorDao;
	
	@Mock
	CourseRepository courseDao;
	
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
	
	@Test
	public void testViewProfessor() {
		//given
		Professor user = new Professor();
		user.setUserId(2);
		user.setUserName("satwika");
		user.setUserEmail("satwika@gmail.com");
		user.setUserPhonenumber(73749282);
		user.setUserPassword("satwika");
		
//		
		List<Professor> users = new ArrayList<>();
		users.add(user);
		professorDao.save(user);
		when(professorDao.findAll()).thenReturn(users);
		
		//when
		List<Professor> profList = professorOp.viewProfessor();
		
		//then
		assertEquals(1, profList.size());
	}
	
	@Test
	public void testGetProfessorById()
	{
		Professor user = new Professor();
		user.setUserId(1);
		user.setUserName("dixit");
		user.setUserEmail("dixit@gmail.com");
		user.setUserPhonenumber(7374922);
		user.setUserPassword("dixit");
		professorDao.save(user);
		
		when(professorOp.getProfessorById("dixit@gmail.com")).thenReturn(user.getUserId());
		
		//when
		int i = professorOp.getProfessorById("dixit@gmail.com");
		
		//then
		assertEquals(user.getUserId(), i);
	}
	
	@Test
	public void  testSetGrades() throws UserNotFoundException, CourseNotFoundException, CourseNotAssignedException
	{
		//Pending due to exception handling
        // doThrow(new RuntimeException()).when(professorOp).setGrades(1,1,"1","A");
        // professorOp.setGrades(1,1,"1","A");
        // verify(professorOp,times(1)).setGrades(1,1,"1","A");
	}
	
	@Test
	public void  testRequestCourseOffering() throws CourseNotFoundException, UserNotFoundException
	{
		//Pending due to exception handling
//		List<Integer> courseIdList = new ArrayList<>();
//		courseIdList.add(1);
//        doThrow(new RuntimeException()).when(professorOp).requestCourseOffering(1,courseIdList);
//        professorOp.requestCourseOffering(1,courseIdList);
//        verify(professorOp,times(1)).requestCourseOffering(1,courseIdList);
	}
	
	@Test
	public void testRegisterProfessor() throws UserAlreadyExistsException
	{
		//Pending due to exception Handling
//		Professor user = new Professor();
//		user.setUserId(1);
//		user.setUserName("satwika");
//		user.setUserEmail("satwika@gmail.com");
//		user.setUserPhonenumber(73749282);
//		user.setUserPassword("satwika");
//		professorDao.save(user);
//		
//        doThrow(new RuntimeException()).when(professorOp).registerProfessor(user);
//        professorOp.registerProfessor(user);
//        verify(professorOp,times(1)).registerProfessor(user);
	}
	
	@Test
	public void testViewCourseCatalog()
	{	
		//Unable to figureout why it's not working
		CourseCatalog course = new CourseCatalog();
		course.setCourseId("1");
		course.setCourseName("Java");
		course.setPrerequisites("None");
		course.setProfessorName("brown");
		courseDao.save(course);
		
		List<CourseCatalog> courses = new ArrayList<>();
		courses.add(course);
		when(professorOp.viewCourseCatalog()).thenReturn(professorOp.viewCourseCatalog());
		
		//when
		List<CourseCatalog> testCourseList = professorOp.viewCourseCatalog();
		
		//then
		assertEquals(courses.size(),testCourseList.size());
		
	}

	@Test
	public void testViewStudentList()
	{
		//Pending due to exception Handling
	}
	
	@Test
	public void testListOfApprovedCourses()
	{
		//Pending due to exception Handling
	}
	
	
}
