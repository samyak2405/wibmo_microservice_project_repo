/**
 * 
 */
package com.wibmo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.entity.User;
import com.wibmo.repository.ProfessorRepository;
import com.wibmo.repository.StudentRepository;

/**
 * 
 */
@Service
public class UserDetailsService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ProfessorRepository professorRepository;
	
	
	public User getUserByEmail(String role,String userEmail)
	{
		User user=null;
		
		if(role.equals("student"))
		{
			user=studentRepository.findByUserEmail(userEmail);
		}
		
		if(role.equals("professor"))
		{
			user=professorRepository.findByUserEmail(userEmail);
		}
		return user;
		
	}

}
