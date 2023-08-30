/**
 * 
 */
package com.wibmo.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wibmo.dto.NotificationDto;
import com.wibmo.entity.Notification;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.service.NotificationOperation;

/**
 * Notification REST Controller
 */
@RestController
public class CRSNotificationController {

	@Autowired
	private NotificationOperation notificationOp;

	/**
	 * To send notifications
	 * @param userId
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approvecourseregistration")
	public ResponseEntity<Object> sendNotification(@RequestBody NotificationDto notificationDto) {
		notificationOp.sendNotification(notificationDto.getUserId(), notificationDto.getNotificationMessage());
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * To view notifications
	 * @param userId
	 * @return List<Notification> contains list of all notifications for a particular userId
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, 
			value = "/approvecourseregistration/{userId}")
	public ResponseEntity<List<Notification>> viewNotification(@PathVariable("userId")int userId) {
		List<Notification> notifications;
		try {
			notifications = notificationOp.getNotificationMessage(userId);
			return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<List<Notification>>(HttpStatus.NOT_FOUND);
		}
	}
}
