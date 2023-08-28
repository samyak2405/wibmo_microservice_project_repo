/**
 * 
 */
package com.wibmo.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.service.AdminOperation;
import com.wibmo.service.AuthenticationOperation;
import com.wibmo.service.ProfessorOperation;
import com.wibmo.dto.UpdatePasswordDto;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.entity.LoginRequest;
import com.wibmo.entity.User;
import com.wibmo.service.StudentOperation;

/**
 * 
 */
@RestController
public class CRSClientController 
{	
	@Autowired
	private StudentOperation studentOp ;
	
	@Autowired
	private ProfessorOperation professorOp ;
	
	@Autowired
	private AdminOperation adminOp ;
	
	@Autowired
	AuthenticationOperation loggedin;
	
	
	public Logger log=LogManager.getLogger();
	
	
	
	
	/**
	 * User login 
	 * @param role
	 * @param loginrequest
	 * @return message if user is successfully logged in or not.
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON,
			method = RequestMethod.POST,
			value = "/login/{role}")
	public ResponseEntity<StringBuilder> loginRequest(@PathVariable int role, 
			@RequestBody LoginRequest loginrequest)
	{
		StringBuilder msg = new StringBuilder();
		if(loggedin.loggedin(loginrequest.getUserEmail(), loginrequest.getUserPassword(),role,msg)) {
    		switch(role) {
    		case 1:
    			return new ResponseEntity<StringBuilder>(msg, HttpStatus.OK);
			case 2:
    			//Professor
				return new ResponseEntity<StringBuilder>(msg, HttpStatus.OK);
			case 3: 
    			
				return new ResponseEntity<StringBuilder>(msg, HttpStatus.OK);
    		}
    		}
		return new ResponseEntity<StringBuilder>(msg, HttpStatus.NOT_FOUND);
		
	}
	/**
	 * User Registration
	 * @param role
	 * @param user
	 * @return message if user is successfully registered or not.
	 */
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON,
			method = RequestMethod.POST,
			value = "/register/{role}")
	public ResponseEntity<String> registerRequest(@PathVariable int role, 
			@RequestBody List<User> users)
	{
		if(role==1)
		{
			try {
				for(User user: users) {
					studentOp.registerStudent(user);
				}
				
				
			} catch (StudentAlreadyRegisteredException e) {
				return new ResponseEntity<String>("Student Already Registerd", HttpStatus.CONFLICT);
			}
		}
		else if(role==2)
		{
			try {
				for(User user: users) {
					professorOp.registerProfessor(user);
				}
			} catch (UserAlreadyExistsException e) {
				return new ResponseEntity<String>("Professor Already Registerd", HttpStatus.CONFLICT);
			}
		}
		else if(role==3)
		{
			try {
				for(User user: users) {
					adminOp.adminRegistration(user);
				}
			} catch (UserAlreadyExistsException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<String>("Admin Already Registerd", HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<String>("Registration Successful", HttpStatus.OK);
	}
	
	
	/**
	 * Update Password
	 * @param role
	 * @param passwordDto
	 * @return message if password is updated successfully or not.
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON,
			method = RequestMethod.POST,
			value = "/updatepassword/{role}")
	public ResponseEntity<String> updatePasswordRequest(@PathVariable int role, 
			@RequestBody UpdatePasswordDto passwordDto)
	{
		StringBuilder msg= new StringBuilder();
		if(loggedin.loggedin(passwordDto.getUserEmail(), passwordDto.getUserPassword(),role,msg)) 
		{
			
			loggedin.updatePassword(passwordDto.getUserEmail(), passwordDto.getUserPasswordNew(), role);
			return new ResponseEntity<String>("Password Updated Successfully", HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<String>("User does not exists", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
}
