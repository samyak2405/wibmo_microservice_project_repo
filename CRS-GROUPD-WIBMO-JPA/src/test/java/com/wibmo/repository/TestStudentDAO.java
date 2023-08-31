package com.wibmo.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wibmo.entity.*;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.service.*;




@ExtendWith(MockitoExtension.class)
public class TestStudentDAO {
	@InjectMocks
	StudentOperationImpl studentOp;
	
	@Mock
	StudentRepository studentDao;
	
	@Test
	public void testRegisterCourses() {
		
        doNothing().when(studentDao).registerCourses(1);

        studentDao.registerCourses(1);

        verify(studentDao,times(1)).registerCourses(1);
	
	}
	
	@Test
	public void testDropCourses() {
		doNothing().when(studentDao).dropCourses(1,"1");

        studentDao.dropCourses(1,"1");

        verify(studentDao,times(1)).dropCourses(1,"1");
	
	}
	
	@Test
	public void testIsApproved() {

		when(studentDao.isApproved(1)).thenReturn(1);
		int flag=studentOp.isApproved(1);
		assertEquals(1,flag);
		
	}
	@Test
	public void testFindByEmail() {
		
		when(studentDao.findByEmail("satwikakarra@gmail.com")).thenReturn(2);
		int studentId=studentOp.getStudentByEmail("satwikakarra@gmail.com");
		
		assertEquals(2,studentId);
		
	}
	
	@Test
	public void testIsStudentRegistered() {
		when(studentDao.isStudentRegistered(1)).thenReturn(1);
		int flag=studentOp.isStudentRegistered(1);
		assertEquals(1,flag);
		
	}
	
	@Test
	public void testlistCourse() throws UserNotApprovedException {
		
		
		List<Object[]> expected = new ArrayList<>() ;
		Object[] obj = new Object[2];
		obj[0]=1;
		obj[1]="Math";
		expected.add(obj);

		when(studentDao.listCourse(1)).thenReturn(expected);
		Map<Integer, String> list=studentOp.listCourse(1);
		
		for(Entry<Integer,String> entry:list.entrySet())
			System.out.println(entry.getKey()+" "+entry.getValue());
		
		assertEquals(list.size(),1);
		
	}
	
	
}
