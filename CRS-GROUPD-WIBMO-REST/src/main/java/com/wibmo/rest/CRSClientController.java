/**
 * 
 */
package com.wibmo.rest;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Loggers;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	public Logger log;
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON,
			method = RequestMethod.POST,
			value = "/login/{role}")
	public String loginRequest(@RequestParam int role, 
			@RequestBody LoginRequest loginrequest)
	{
		if(loggedin.loggedin(loginrequest.getUserEmail(), loginrequest.getUserPassword(),role)) {
    		switch(role) {
    		case 1:
    			return "\nYou are logged in successfully as a student";
			case 2:
    			//Professor
    			return "You are logged in successfully as a Professor";
			case 3: 
    			
    			return "You are logged in successfully as a Admin";
    		}
    		}
		return "Invalid Credentials";
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON,
			method = RequestMethod.POST,
			value = "/register/{role}")
	public String registerRequest(@RequestParam int role, 
			@RequestBody User user) throws StudentAlreadyRegisteredException, UserAlreadyExistsException
	{
		if(role==1)
		{
			studentOp.registerStudent(user);
		}
		else if(role==2)
		{
			professorOp.registerProfessor(user);
		}
		else if(role==3)
		{
			adminOp.adminRegistration(user);
		}
		return "Registration Successful";
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON,
			method = RequestMethod.POST,
			value = "/updatepassword/{role}")
	public String updatePasswordRequest(@RequestParam int role, 
			@RequestBody LoginRequest loginrequest) throws StudentAlreadyRegisteredException, UserAlreadyExistsException
	{
		
		if(loggedin.loggedin(loginrequest.getUserEmail(), loginrequest.getUserPassword(),role)) {
			log.info("\nChange Password");
			String passwordOne = clientValidator.passwordValidator();
			loggedin.updatePassword(loginrequest.getUserEmail(), passwordOne, role);
			return null;
		}
		else {
			log.info("invalid credentials");
			log.info("");
       	    log.info("===================================================================================");
			return null;
		}
	}
	
	
	
	
	
	
}
