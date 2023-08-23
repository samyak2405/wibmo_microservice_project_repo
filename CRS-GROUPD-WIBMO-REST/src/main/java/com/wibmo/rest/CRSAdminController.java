/**
 * 
 */
package com.wibmo.rest;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.model.Admin;
import com.wibmo.service.AdminOperation;
import com.wibmo.service.AdminOperationImpl;
import com.wibmo.validator.ClientValidatorImpl;

/**
 * 
 */

@RestController
public class CRSAdminController {
	
	@Autowired
	public AdminOperation adminOp;
	
	@Autowired
	public ClientValidatorImpl clientValidator;
	
	
	public Logger log=LogManager.getLogger();
	
	String userEmail;
	int adminId;
	

	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approveallstudents")
	public void approveAllStudents()
	{
		adminOp.approveStudent();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approvestudentbyid/{id}")
	public void approveStudentById(@PathVariable int id) throws UserNotFoundException
	{
		adminOp.approveStudentById(id);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approvecourseregistration")
	public void approveCourseRegistration()
	{
		adminOp.approveCourseRegistration();
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/assigncourseprof")
	public void assignCourseProf() {
		adminOp.assignCoursesProf();
	}
	
	
	
	
	
	
	
	
}
