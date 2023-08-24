/**
 * 
 */
package com.wibmo.rest;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Loggers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.service.AdminOperation;
import com.wibmo.service.AuthenticationOperation;
import com.wibmo.service.AuthenticationOperationImpl;
import com.wibmo.service.ProfessorOperation;
import com.wibmo.dto.UpdatePasswordDto;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserAlreadyExistsException;
//import com.wibmo.client.CRSAdminMenu;
//import com.wibmo.client.CRSProfessorMenu;
//import com.wibmo.client.CRSStudentMenu;
import com.wibmo.model.LoginRequest;
import com.wibmo.model.User;
import com.wibmo.service.StudentOperation;
import com.wibmo.service.StudentOperationImpl;
import com.wibmo.validator.ClientValidatorImpl;

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
	private ClientValidatorImpl clientValidator;
	
	
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
	public ResponseEntity loginRequest(@PathVariable int role, 
			@RequestBody LoginRequest loginrequest)
	{
		if(loggedin.loggedin(loginrequest.getUserEmail(), loginrequest.getUserPassword(),role)) {
    		switch(role) {
    		case 1:
    			return new ResponseEntity("You are logged in successfully as a student", HttpStatus.OK);
			case 2:
    			//Professor
				return new ResponseEntity("You are logged in successfully as a Professor", HttpStatus.OK);
			case 3: 
    			
				return new ResponseEntity("You are logged in successfully as a Admin", HttpStatus.OK);
    		}
    		}
		return new ResponseEntity("Invalid Credentials", HttpStatus.NOT_FOUND);
		
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
	public ResponseEntity registerRequest(@PathVariable int role, 
			@RequestBody User user)
	{
		if(role==1)
		{
			try {
				studentOp.registerStudent(user);
				
			} catch (StudentAlreadyRegisteredException e) {
				return new ResponseEntity("Student Already Registerd", HttpStatus.CONFLICT);
			}
		}
		else if(role==2)
		{
			try {
				professorOp.registerProfessor(user);
				
			} catch (UserAlreadyExistsException e) {
				return new ResponseEntity("Professor Already Registerd", HttpStatus.CONFLICT);
			}
		}
		else if(role==3)
		{
			try {
				adminOp.adminRegistration(user);
				
			} catch (UserAlreadyExistsException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity("Admin Already Registerd", HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity("Registration Successful", HttpStatus.OK);
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
	public ResponseEntity updatePasswordRequest(@PathVariable int role, 
			@RequestBody UpdatePasswordDto passwordDto)
	{
		
		if(loggedin.loggedin(passwordDto.getUserEmail(), passwordDto.getUserPassword(),role)) 
		{
			
			loggedin.updatePassword(passwordDto.getUserEmail(), passwordDto.getUserPasswordNew(), role);
			return new ResponseEntity("Password Updated Successfully", HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity("User does not exists", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
}
