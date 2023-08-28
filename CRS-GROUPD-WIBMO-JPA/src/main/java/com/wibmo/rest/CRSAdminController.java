/**
 * 
 */
package com.wibmo.rest;

import java.util.ArrayList;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.exception.UserNotFoundException;
import com.wibmo.entity.*;
import com.wibmo.service.AdminOperation;
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
	
	/**
	 * To approve all the registered students at once
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approveallstudents")
	public ResponseEntity<String> approveAllStudents()
	{
		
		adminOp.approveStudent();	
		return new ResponseEntity<String>("Approved All Students", HttpStatus.OK);
	}
	
	
	/**
	 * To approve a student by id
	 * @param id
	 * @return
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/approvestudentbyid/{id}")
	public ResponseEntity<String> approveStudentById(@PathVariable int id) 
	{
		try {
			adminOp.approveStudentById(id);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Student with id "+e.getUserId()+" is not found. ",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Approved Registration of student with ID: "+id,HttpStatus.OK);
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
		return new ResponseEntity<List<String>>(responseMessage,HttpStatus.OK);
	}
	
	/**
	 * To assign a specific course to a professor
	 * @return
	 */
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON, 
			method = RequestMethod.PUT, 
			value="/assigncourseprof")
	public ResponseEntity<String> assignCourseProf() {
		adminOp.assignCoursesProf();
		return new ResponseEntity<String>("Course is assigned to professor", HttpStatus.OK);
		
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
