/**
 * 
 */
package com.wibmo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.Notification;
import com.wibmo.dto.AddCourseDto;
import com.wibmo.dto.DropCourseDTO;
import com.wibmo.dto.GradeCardResponseDTO;
import com.wibmo.exception.CourseLimitExceededException;
import com.wibmo.exception.CourseNotFoundException;
import com.wibmo.exception.StudentAlreadyRegisteredException;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.jwt.JwtTokenUtils;
import com.wibmo.service.NotificationOperation;
import com.wibmo.service.PaymentOperation;
import com.wibmo.service.StudentOperation;

import net.bytebuddy.asm.Advice.Return;

/**
 * 
 */
@RestController
@RequestMapping(value="/api/student")
@CrossOrigin
@PreAuthorize("hasAuthority('Role.student')")
public class CRSStudentController {

	@Autowired
	public StudentOperation studentOp;
	
	@Autowired
	private PaymentOperation payment;
	
	@Autowired
	private NotificationOperation notificationOp;
	

	/**
	 * To add course preferences for the registration.
	 * 
	 * @param userId
	 * @param addCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value = "/{id}/addCourse", method = RequestMethod.POST)
	public ResponseEntity<String> addCourse(@PathVariable(value = "id") int userId,
			@RequestBody AddCourseDto addCourseDto,@RequestHeader(value="Authorization") String jwt) 
	{
		if(!studentOp.innerAuthenticate(userId, jwt))
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
			
		int isRegistered = studentOp.isStudentRegistered(userId);
		if (isRegistered >= 1)
			return new ResponseEntity<String>("\"Your Registration is completed. You can't add courses\";",
					HttpStatus.CONFLICT);
		try {
			studentOp.addCourses(userId, addCourseDto);
			return new ResponseEntity<String>("Course Added Successfully", HttpStatus.OK);

		} catch (CourseNotFoundException e) {
			return new ResponseEntity<String>("No Course added", HttpStatus.NOT_FOUND);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);

		} catch (CourseLimitExceededException e) {
			return new ResponseEntity<String>("Course Limit Exceeded", HttpStatus.CONFLICT);
		}

	}

	/**
	 * To drop an already added course
	 * 
	 * @param dropCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value = "/{id}/dropCourse", method = RequestMethod.POST)
	public ResponseEntity<String> dropCourse(@RequestBody DropCourseDTO dropCourseDto,
			@RequestHeader(value="Authorization") String jwt) {
		if(!studentOp.innerAuthenticate(dropCourseDto.getStudentId(), jwt))
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
			
		
		int isRegistered = studentOp.isStudentRegistered(dropCourseDto.getStudentId());
		if (isRegistered >= 1) {
			return new ResponseEntity<String>("\"Your Registration is completed. You can't drop courses\";",
					HttpStatus.CONFLICT);
		}
		try {
			int coursePref = studentOp.dropCourses(dropCourseDto.getStudentId(), dropCourseDto.getCourseId());

			if (coursePref == 0) {
				return new ResponseEntity<String>("You have to add Complusory course", HttpStatus.CONFLICT);

			}
			if (coursePref == 1) {
				return new ResponseEntity<String>("You have to add Alternative course", HttpStatus.CONFLICT);
			}
		} catch (UserNotFoundException e) {

			return new ResponseEntity<String>("User with id " + e.getUserId() + " is not found", HttpStatus.NOT_FOUND);

		} catch (CourseNotFoundException e) {
			return new ResponseEntity<String>("Course with id " + e.getCourseId() + " is not found",
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Drop is Successful!! ", HttpStatus.OK);
	}

	/**
	 * To apply for the course registration after the addition of courses has been
	 * completed.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{id}/registerCourse", method = RequestMethod.POST)
	public ResponseEntity<?> registerCourses(@PathVariable(value = "id") int userId,
			@RequestHeader(value="Authorization") String jwt) {
		if(!studentOp.innerAuthenticate(userId, jwt))
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		try {
			studentOp.registerCourses(userId);
			return ResponseEntity.ok("Applied For Course Registration Successfully. Now go to billing Section");
		} catch (UserNotFoundException e) {

			return new ResponseEntity<String>("User with id " + e.getUserId() + " is not found", HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * To return list of registered courses
	 * 
	 * @param userId
	 * @return a map of registered courses and their course names.
	 */
	@RequestMapping(value = "/{id}/listCourse", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> listCourse(@PathVariable(value = "id") int userId,
			@RequestHeader(value="Authorization") String jwt) {
		if(!studentOp.innerAuthenticate(userId, jwt))
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		try {
			Map<String, String> courses = studentOp.listCourse(userId);
			if (courses.size() == 0) {

				return new ResponseEntity("Course Registration pending", HttpStatus.TOO_EARLY);

			}
			return ResponseEntity.ok(courses);

		} catch (UserNotApprovedException e) {
			return new ResponseEntity("User with id " + e.getUserId() + " is not approved by admin",
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * To view the grade card
	 * 
	 * @param userId
	 * @return a list of grades.
	 */
	@RequestMapping(value = "/{id}/viewReportCard", method = RequestMethod.GET)
	public ResponseEntity<GradeCardResponseDTO> viewReportCard(@PathVariable(value = "id") int userId,
			@RequestHeader(value="Authorization") String jwt) {
		
		if(!studentOp.innerAuthenticate(userId, jwt))
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
			
		try {
			return ResponseEntity.ok(studentOp.viewReportCard(userId));
		} catch (UserNotApprovedException e) {
			return new ResponseEntity("Courses of student with id " + e.getUserId() + " is not approved by admin",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/viewCourseCatalog", method = RequestMethod.GET)
	public ResponseEntity<List<CourseCatalog>> viewCourseCatalog() {
		return ResponseEntity.ok(studentOp.viewCourseCatalog());
	}

	/**
	 * To view the notifications
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/{id}/viewNotifications", method = RequestMethod.GET)
	public ResponseEntity viewNotifications(@PathVariable(name = "id") int studentId) {
		try {
			List<Notification> notifications = notificationOp.getNotificationMessage(studentId);
			if (notifications != null) {
				return ResponseEntity.ok(notifications);

			} else {
				return new ResponseEntity("No Notifications", HttpStatus.NOT_FOUND);
			}
		}

		catch (UserNotFoundException e) {
			return new ResponseEntity<String>("User with id " + e.getUserId() + " is not found", HttpStatus.NOT_FOUND);
		}
	}
	
    


}
