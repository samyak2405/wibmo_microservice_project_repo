/**
 * 
 */
package com.wibmo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wibmo.entity.Notification;
import com.wibmo.entity.NotificationStudentMapping;
import com.wibmo.entity.Student;
import com.wibmo.repository.*;

/**
 * 
 */

@Service
public class NotificationOperationImpl implements NotificationOperation {


	@Autowired
	public NotificationStudentMappingRepository notificationStudentMappingRepository;
	

	@Autowired
	public NotificationRepository notificationRepository;
	
	@Autowired
	public StudentRepository studentRepository;
	
	@Override
	public List<Notification> getNotificationMessage(int studentId) 
	{
		List<NotificationStudentMapping> notificationMappings=notificationStudentMappingRepository.findByStudent(studentRepository.findById(studentId).get());
		List<Notification>notifications=notificationMappings.stream().map(notifs->new Notification(notifs.getNotification())).collect(Collectors.toList());
		return notifications;
	}

	@Override
	public void sendNotification(int notificationId, int userId) 
	{
		notificationStudentMappingRepository.save(
				new NotificationStudentMapping(studentRepository.findById(userId).get(),notificationRepository.findById(notificationId).get()));
	}

	
	
	
}
