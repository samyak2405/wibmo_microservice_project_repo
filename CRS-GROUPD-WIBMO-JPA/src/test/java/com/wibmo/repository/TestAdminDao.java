/**
 * 
 */
package com.wibmo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wibmo.entity.Admin;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.AdminOperationImpl;

import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
public class TestAdminDao {

	@InjectMocks
	private AdminOperationImpl adminOp;
	
	@Mock
	AdminRepository adminRepo;
	
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
		
		adminOp.approveAdmin(userId);
		
		verify(adminRepo,times(1)).setAdminApproval(userId);
	}
	
	/**
	 * Test for approve student by Id
	 * @throws UserNotFoundException 
	 */
	@Test
	public void approveStudentById(){
		int id = 1;
		
		try {
			doNothing().when(adminRepo).approveStudentRegistrationById(id);
			adminOp.approveStudentById(id);
			verify(adminRepo,times(1)).approveStudentRegistrationById(id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
			
		
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
