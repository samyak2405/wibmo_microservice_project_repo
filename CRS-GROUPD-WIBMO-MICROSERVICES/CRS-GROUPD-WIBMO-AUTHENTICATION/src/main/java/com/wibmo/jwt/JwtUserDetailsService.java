/**
 * 
 */
package com.wibmo.jwt;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);
	
	/**
	 * @param param 
	 * @throws UsernameNotFoundException
	 * @return UserDetails
	 */

	@Override
	public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
		
		String values[]=param.split("#");
		com.wibmo.entity.User user = userDeatails.getUserByEmail(values[1],values[0]);
		
		log.debug(values[0]);
		log.debug(values[1]);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + values[0]);
		}
		Set<SimpleGrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority("Role." + values[1]));
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(),
				roles);
	}



}