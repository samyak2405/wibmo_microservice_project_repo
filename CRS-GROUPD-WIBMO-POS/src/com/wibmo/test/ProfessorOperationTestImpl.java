package com.wibmo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.business.ProfessorOperationImpl;

public class ProfessorOperationTestImpl {

	ProfessorOperationImpl profop = null;
	@Before
	public void setUp() throws Exception {
		profop = new ProfessorOperationImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	
	
	@Test
	public void testgetProfessorById()
	{
		int profid1 = profop.getProfessorById("brown@gmail.com");
		
		
		assertEquals(1, profid1);
		
	}
}
