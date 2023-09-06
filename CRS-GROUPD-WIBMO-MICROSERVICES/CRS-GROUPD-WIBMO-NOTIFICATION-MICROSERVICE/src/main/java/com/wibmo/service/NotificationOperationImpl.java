package com.wibmo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.wibmo.entity.Notification;
import com.wibmo.entity.NotificationStudentMapping;
import com.wibmo.exception.UserNotFoundException;
import com.wibmo.repository.*;
import com.wibmo.dto.*;

/**
 * Notification Service Implementation
 */
@Service
public class NotificationOperationImpl implements NotificationOperation {

	@Autowired
	public NotificationStudentMappingRepository notificationStudentMappingRepository;

	@Autowired
	public NotificationRepository notificationRepository;

	@Autowired
	public StudentRepository studentRepository;

	/**
	 * To get notifications of a specific user
	 * 
	 * @param studentId
	 * @return list of notifications
	 */
	
	@Override
	@Cacheable(value="Notification", key="#studentId")
	public List<Notification> getNotificationMessage(int studentId) throws UserNotFoundException {
		if (studentRepository.findById(studentId).isEmpty() == true) {
			throw new UserNotFoundException(studentId);
		}
		List<NotificationStudentMapping> notificationMappings = notificationStudentMappingRepository
				.findByStudent(studentRepository.findById(studentId).get());
		List<Notification> notifications = notificationMappings.stream()
				.map(notifs -> new Notification(notifs.getNotification())).collect(Collectors.toList());
		return notifications;
	}

	/**
	 * Send notification to user
	 * @param userId
	 * @param notificationMessage
	 */
	@CachePut(value="Notification", key="#id")
	public void sendNotification(NotificationDto notifications) {
		notificationStudentMappingRepository.save(new NotificationStudentMapping(
				studentRepository.findById(notifications.getUserId()).get(),
				notificationRepository.findByNotificationMessage(notifications.getNotificationMessage())));
	}

}
