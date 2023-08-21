/**
 * 
 */

package com.wibmo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.business.StudentOperationImpl;
/**
 * @author gavaniya.ketanbhai
 *
 */
public class StudentOperationTestImpl 
{
	StudentOperationImpl studop = null;
	@Before
	public void setUp() throws Exception {
		studop = new StudentOperationImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	
	
	@Test
	public void testgetStudentByEmail()
	{
		int studid1 = studop.getStudentByEmail("dixit@gmail.com");
		int studid2 = studop.getStudentByEmail("samyak@gmail.com");
		
		assertEquals(1, studid1);
		assertEquals(2, studid2);
	}
	
	@Test
	public void testIsStudentApproved()
	{
		boolean studid1 = studop.isApproved(1);
		boolean studid2 = studop.isApproved(2);
		
		assertEquals(false, studid1);
		assertEquals(false, studid2);
	}
	
	@Test
	public void testIsStudentRegistered()
	{
		int studid1 = studop.isStudentRegistered(1);
		int studid2 = studop.isStudentRegistered(2);
		
		assertEquals(1, studid1);
		assertEquals(1, studid2);
	}
}
