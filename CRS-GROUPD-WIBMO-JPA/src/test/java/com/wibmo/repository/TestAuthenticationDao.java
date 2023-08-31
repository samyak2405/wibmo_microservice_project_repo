/**
 * 
 */
package com.wibmo.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wibmo.service.AuthenticationOperationImpl;


/**
 * 
 */
@ExtendWith(MockitoExtension.class)
public class TestAuthenticationDao {

	@InjectMocks
	AuthenticationOperationImpl authOp;
	
	@Mock
	AuthenticationRepository authRepo;
	
	/**
	 * Test user Login
	 */
	@Test
	public void loggedInTest() {
		
	}
	
	/**
	 * Test Update Password
	 */
	@Test
	public void updatePasswordTest() {
		
	}
}
