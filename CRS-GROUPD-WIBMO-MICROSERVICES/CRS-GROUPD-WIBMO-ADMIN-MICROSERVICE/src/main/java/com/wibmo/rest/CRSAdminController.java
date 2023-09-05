package com.wibmo.rest;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wibmo.exception.UserAlreadyApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.AdminOperation;

/**
 * Admin Controller
 */
@RestController
@RequestMapping(value = "/api/admin")
@CrossOrigin
@PreAuthorize("hasAuthority('Role.admin')")
public class CRSAdminController {

	@Autowired
	public AdminOperation adminOp;

	/**
	 * To approve all the registered students at once
	 * 
	 * @return Response Status 200(OK)
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approveallstudents")
	public ResponseEntity<String> approveAllStudents() {

		adminOp.approveStudent();
		return new ResponseEntity<String>("Approved All Students", HttpStatus.OK);
	}

	/**
	 * To approve a student by id
	 * 
	 * @param id
	 * @return Response status 200(OK) if student is approved with id otherwise
	 *         404(NOT_FOUND)
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approvestudentbyid/{id}")
	public ResponseEntity<String> approveStudentById(@PathVariable int id) {
		try {
			adminOp.approveStudentById(id);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Student with id " + e.getUserId() + " is not found. ",
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Approved Registration of student with ID: " + id, HttpStatus.OK);
	}

	/**
	 * To approve the course Registration of all the students.
	 * 
	 * @return Response status 200(OK)
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approvecourseregistration")
	public ResponseEntity<List<String>> approveCourseRegistration() {
		Map<Integer, Boolean> registrationStatus = adminOp.approveCourseRegistration();

		List<String> responseMessage = new ArrayList<>();

		for (Entry<Integer, Boolean> entry : registrationStatus.entrySet()) {
			if (entry.getValue())
				responseMessage.add("Student Registration Successful with student ID : " + entry.getKey());
			else
				responseMessage.add("Student Registration Unsuccessful with student ID : " + entry.getKey());
		}
		return new ResponseEntity<List<String>>(responseMessage, HttpStatus.OK);
	}

	/**
	 * To assign a specific course to a professor
	 * 
	 * @return Response status 200(OK)
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/assigncourseprof")
	public ResponseEntity<String> assignCourseProf() {
		adminOp.assignCoursesProf();
		return new ResponseEntity<String>("Course is assigned to professor", HttpStatus.OK);

	}

	/**
	 * To assign a specific course to a professor
	 * 
	 * @return Response status 200(OK)
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approveadmin/{userId}")
	public ResponseEntity<String> approveAdmin(@PathVariable("userId") int userId) {
		try {
			adminOp.approveAdmin(userId);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("User Does not Exists with userId" + userId, HttpStatus.NOT_FOUND);
		} catch (UserAlreadyApprovedException e) {
			return new ResponseEntity<String>("User is Already approved!", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Admin with Id: " + userId + " approved by Primary Admins", HttpStatus.OK);

	}

}
