/**
 * 
 */
package com.wibmo.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.bean.Admin;
import com.wibmo.business.AdminOperation;
import com.wibmo.business.AdminOperationImpl;
import com.wibmo.dao.AdminDAO;
import com.wibmo.dao.AdminDAOImpl;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;

/**
 * 
 */
public class AdminOperationTest {

	
	public AdminOperation adminOp=null;
	
    final private static Logger logger = Logger.getLogger(AdminOperationTest.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		adminOp=new AdminOperationImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		logger.info("\nTest Completed");
	}

	/**
	 * Test method for {@link com.wibmo.business.AdminOperation#approveStudent()}.
	 */
	@Test
	public void testApproveStudent() {
		adminOp.approveStudent();
	}

	/**
	 * Test method for {@link com.wibmo.business.AdminOperation#approveCourseRegistration()}.
	 */
	@Test
	public void testApproveCourseRegistration() {
		adminOp.approveCourseRegistration();
	}

	/**
	 * Test method for {@link com.wibmo.business.AdminOperation#addAdmin(com.wibmo.bean.Admin)}.
	 */
	@Test
	public void testAddAdmin() {
		Admin admin=new Admin();
		admin.setUserEmail("sath@gmail.com");
		admin.setUserName("sathAdmin");
		admin.setUserPassword("sath");
		admin.setUserPhonenumber(12345678);
		adminOp.addAdmin(admin);

	}

	/**
	 * Test method for {@link com.wibmo.business.AdminOperation#assignCoursesProf()}.
	 */
	@Test
	public void testAssignCoursesProf() {
		adminOp.assignCoursesProf();
	}

	/**
	 * Test method for {@link com.wibmo.business.AdminOperation#adminRegistration(com.wibmo.bean.Admin)}.
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public void testAdminRegistration() throws UserAlreadyExistsException {
		Admin admin=new Admin();
		admin.setUserEmail("sid@gmail.com");
		admin.setUserName("sidAdmin");
		admin.setUserPassword("sid");
		admin.setUserPhonenumber(12345678);
		adminOp.adminRegistration(admin);
		
	}

	/**
	 * Test method for {@link com.wibmo.business.AdminOperation#approveStudentById()}.
	 * @throws UserNotFoundException 
	 */
	@Test
	public void testApproveStudentById() throws UserNotFoundException {
		adminOp.approveStudentById();
		
	}

}
