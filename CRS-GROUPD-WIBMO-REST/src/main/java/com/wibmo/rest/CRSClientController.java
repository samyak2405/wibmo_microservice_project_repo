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
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.service.AuthenticationOperation;
import com.wibmo.service.AuthenticationOperationImpl;
//import com.wibmo.client.CRSAdminMenu;
//import com.wibmo.client.CRSProfessorMenu;
//import com.wibmo.client.CRSStudentMenu;
import com.wibmo.model.LoginRequest;
import com.wibmo.service.StudentOperation;
import com.wibmo.service.StudentOperationImpl;
import com.wibmo.validator.ClientValidatorImpl;

/**
 * 
 */
@RestController
@RequestMapping("/api")
public class CRSClientController 
{	
	@Autowired
	AuthenticationOperation loggedin;
	
	@Autowired
	public StudentOperation studentOp;
	
	@Autowired
	public  ClientValidatorImpl clientValidator;
	
	@Autowired
	public Logger log;
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.POST, 
			value="/login")
	public String studentLogin(@RequestBody LoginRequest loginRequest)
	{
		int role=1;
		if(loggedin.loggedin(loginRequest.getUserEmail(), loginRequest.getUserPassword(),role)) {
    		switch(role) {
    		case 1:
    			System.out.println("hellow");
    			return "\nYou are logged in successfully as a student";
			case 2:
    			//Professor
    			log.info("");
    			return "You are logged in successfully as a Professor";
			case 3: 
    			log.info("");
    			return "You are logged in successfully as a Admin";
    		}
    		}
    		else {
    			return "\nInvalid credentials";
    		}
		return null;
	}
	
}
