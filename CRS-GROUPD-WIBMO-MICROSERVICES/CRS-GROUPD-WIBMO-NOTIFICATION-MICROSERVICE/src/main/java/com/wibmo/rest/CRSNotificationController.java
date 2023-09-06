/**
 * 
 */
package com.wibmo.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@KafkaListener(topics="student")
@RestController
@RequestMapping(value="/api/notification")
@CrossOrigin
public class CRSNotificationController {

	@Autowired
	private NotificationOperation notificationOp;

	@Autowired
    KafkaTemplate<String, String> kafkaTemplate;
	
	/**
	 * To send notifications
	 * @param userId
	 */
	
//	@KafkaListener(topics="student")
	@KafkaHandler
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/sendNotification")
	public ResponseEntity sendNotification(NotificationDto notificationDto) {
		notificationOp.sendNotification(notificationDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	/**
	 * To view notifications
	 * @param userId
	 * @return List<Notification> contains list of all notifications for a particular userId
	 */
//	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, 
//			value = "/getNotification/{userId}")
//	public ResponseEntity<List<Notification>> viewNotification(@PathVariable("userId")int userId) {
//		List<Notification> notifications;
//		try {
//			notifications = notificationOp.getNotificationMessage(userId);
//			
//			return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
//			
//		} catch (UserNotFoundException e) {
//			return new ResponseEntity<List<Notification>>(HttpStatus.NOT_FOUND);
//		}
//	}
}
