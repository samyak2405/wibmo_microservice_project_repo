/**
 * 
 */
package com.wibmo.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.business.AdminOperationImpl;
import com.wibmo.business.AuthenticationOperation;
import com.wibmo.business.AuthenticationOperationImpl;

/**
 * 
 */
public class AuthenticationOperationTest {

	
	/**
	 * @throws java.lang.Exception
	 */
    final private static Logger logger = Logger.getLogger(AuthenticationOperationTest.class);
	AuthenticationOperation authentication=null;
	
	@Before
	public void setUp() throws Exception {
		authentication= new AuthenticationOperationImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		logger.info("\nTest Completed");
	}

	/**
	 * Test method for {@link com.wibmo.business.AuthenticationOperation#loggedin(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testLoggedin() {
		String userEmail="sid";
		String password="newPassword";
		int role=1;
		boolean a=authentication.loggedin(userEmail, password, role);
		assertTrue(a);
	}

	/**
	 * Test method for {@link com.wibmo.business.AuthenticationOperation#updatePassword(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testUpdatePassword() {
		
		String userEmail="sid";
		String password="newPassword";
		int role=1;
		authentication.updatePassword(userEmail, password, role);
	}

}
