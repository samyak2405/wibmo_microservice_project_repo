/**
 * 
 */
package com.wibmo.rest;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	

	final static Logger log=null;
	
	String userEmail;
	int adminId;
	
	public CRSAdminController() {
		
	}
	
	public CRSAdminController(String userEmail) {
		this.userEmail = userEmail;
		adminId = adminOp.getAdminById(userEmail);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.POST, 
			value="/register")
	public void adminRegistration(@RequestBody Admin admin) throws UserAlreadyExistsException
	{
		adminOp.adminRegistration(admin);
	}
	
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
	public void approveStudentById(@RequestParam int id) throws UserNotFoundException
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
