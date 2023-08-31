/**
 * 
 */
package com.wibmo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wibmo.entity.Professor;
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
	
	@Test
	public void findAllProfessor() {
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
}
