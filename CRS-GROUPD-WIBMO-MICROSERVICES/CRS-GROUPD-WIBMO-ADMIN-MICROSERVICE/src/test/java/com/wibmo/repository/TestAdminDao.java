/**
 * 
 */
package com.wibmo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wibmo.entity.Admin;
import com.wibmo.exception.UserAlreadyApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.AdminOperationImpl;

import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
public class TestAdminDao {

	private static final Logger log = LoggerFactory.getLogger(TestAdminDao.class);
	@InjectMocks
	private AdminOperationImpl adminOp;
	
	@Mock
	AdminRepository adminRepo;
	
	@Mock
	StudentRepository studentRepo;
	
	/**
	 * Test to check approve student registration
	 */
	@Test
	public void approveStudentTest() {
		doNothing().when(adminRepo).approveStudentRegistration();
		
		adminOp.approveStudent();
		
		verify(adminRepo,times(1)).approveStudentRegistration();
	}
	
	/**
	 * Test to check add admin
	 */
	@Test
	public void approveAdminTest() {
		int userId = 1;
		
		doNothing().when(adminRepo).setAdminApproval(userId);
		
		try {
			adminOp.approveAdmin(userId);
			verify(adminOp,times(1)).approveAdmin(userId);
		} catch (UserNotFoundException e) {
			log.debug("User not found");
		} catch (UserAlreadyApprovedException e) {
			// TODO Auto-generated catch block
			log.debug("User Already Approved by Admin");
		}
		
		
	}
	
	/**
	 * Test for approve student by Id
	 * @throws UserNotFoundException 
	 */
	@Test
	public void approveStudentById(){
		int id = 1;
		
			doThrow(UserNotFoundException.class).when(studentRepo).findById(id);
			doThrow(new UserNotFoundException(id)).when(adminRepo).approveStudentRegistrationById(1);
//			doNothing().when(adminRepo).approveStudentRegistrationById(id);
			try {
				adminOp.approveStudentById(id);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				log.debug("User not found");
			}
			verify(adminRepo,times(1)).approveStudentRegistrationById(id);
		
			
		
	}
	
	/**
	 * To Test get admin by email
	 */
	@Test void getAdminByEmailTest() {
		when(adminRepo.getAdminById("dixit@gmail.com")).thenReturn(1);
		
		int adminId = adminOp.getAdminByEmail("dixit@gmail.com");
		
		assertEquals(1,adminId);
	}

}
