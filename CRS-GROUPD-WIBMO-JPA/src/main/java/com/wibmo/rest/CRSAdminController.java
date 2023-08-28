/**
 * 
 */
package com.wibmo.rest;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.exception.UserAlreadyExistsException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.entity.*;
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
	
	/**
	 * To approve all the registered students at once
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approveallstudents")
	public ResponseEntity approveAllStudents()
	{
			adminOp.approveStudent();
		return new ResponseEntity("Approved All Students", HttpStatus.OK);
	}
	
	
	/**
	 * To approve a student by id
	 * @param id
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approvestudentbyid/{id}")
	public ResponseEntity approveStudentById(@PathVariable int id) 
	{
		try {
			adminOp.approveStudentById(id);
		} catch (UserNotFoundException e) {
			return new ResponseEntity("Student with id "+e.getUserId()+" is not found. ",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity("Approved Registration of student with ID: "+id,HttpStatus.OK);
	}
	
	/**
	 * To approve the course Registration of all the students.
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approvecourseregistration")
	public ResponseEntity<List<String>> approveCourseRegistration()
	{
		List<Boolean> registrationStatus = adminOp.approveCourseRegistration();
		List<String> responseMessage = new ArrayList<>();
		
		for(Boolean status:registrationStatus) {
			if(status)
				responseMessage.add("Student Registration Successful");
			else
				responseMessage.add("Student Registration Unsuccessful");
		}
		return new ResponseEntity(responseMessage,HttpStatus.OK);
	}
	
	/**
	 * To assign a specific course to a professor
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/assigncourseprof")
	public ResponseEntity assignCourseProf() {
		adminOp.assignCoursesProf();
		return new ResponseEntity("Course is assigned to professor", HttpStatus.OK);
		
	}
	
	
	

	
	/**
	 * To assign a specific course to a professor
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.POST, 
			value="/addadmin")
	public ResponseEntity addAdmin(User user) {
		adminOp.addAdmin(user);
		return new ResponseEntity("Course is assigned to professor", HttpStatus.OK);
		
	}
	
	
	
	
	
	
}
