/**
 * 
 */
package com.wibmo.jwt;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wibmo.entity.User;
import com.wibmo.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService  implements UserDetailsService{

	@Autowired
	public com.wibmo.jwt.UserDetailsService userDeatails;

	
//	
//	public UserDetails loadUserByUsername(String username,String role) throws UsernameNotFoundException {
//		com.wibmo.entity.User user = userDeatails.getUserByEmail(role,username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//		Set<SimpleGrantedAuthority> roles = new HashSet<>();
//		roles.add(new SimpleGrantedAuthority("Role." + role));
//		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
//				roles);
//	}
//
//	public User save(UserRegistrationDetails user) {
//		User newUser = new User();
//		newUser.setUserEmail(user.getUserName());
//		newUser.setPassword(user.getPassword());
//		newUser.setUserType(user.getUserType());
//		userDao.save(newUser);
//		return newUser;
//	}
	

	@Override
	public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
		
		String values[]=param.split("#");
		com.wibmo.entity.User user = userDeatails.getUserByEmail(values[1],values[0]);
		
		System.out.println(values[0]);
		System.out.println(values[1]);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + values[0]);
		}
		Set<SimpleGrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority("Role." + values[1]));
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
				roles);
	}

	public int loadUserIdByUserName(String param)
	{
		
		String values[]=param.split("#");
		com.wibmo.entity.User user = userDeatails.getUserByEmail(values[1],values[0]);
		
		return user.getUserId();
	}


}