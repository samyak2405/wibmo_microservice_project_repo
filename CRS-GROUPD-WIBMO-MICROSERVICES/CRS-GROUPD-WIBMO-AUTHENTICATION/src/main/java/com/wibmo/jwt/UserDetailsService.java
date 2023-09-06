/**
 * 
 */
package com.wibmo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.entity.User;
import com.wibmo.repository.AdminRepository;
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
	private AdminRepository adminRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	
	/**
	 * 
	 * @param role
	 * @param userEmail
	 * @return
	 */
	public User getUserByEmail(String role,String userEmail)
	{
		User user=null;
		
		if(role.equals("student"))
		{
			
			
			int checkIsApproved=studentRepository.countByUserEmailAndIsapproved(userEmail, 1);
			if(checkIsApproved>0)
			{
				user=studentRepository.findByUserEmail(userEmail);
			}
		}
		
		if(role.equals("professor"))
		{
			user=professorRepository.findByUserEmail(userEmail);
		}
		
		if(role.equals("admin"))
		{
			user=adminRepository.findByUserEmail(userEmail);
		}
		
		return user;
		
	}

}
