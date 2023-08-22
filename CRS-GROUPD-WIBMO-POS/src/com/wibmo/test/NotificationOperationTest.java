package com.wibmo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.bean.Notification;
import com.wibmo.business.NotificationOperation;
import com.wibmo.business.NotificationOperationImpl;
import com.wibmo.constant.NotificationConstants;

public class NotificationOperationTest {
     Notification notification =null;
     NotificationOperation notificationoperation=null;
	@Before
	public void setUp() throws Exception {
		notificationoperation=new NotificationOperationImpl();
		notification=new Notification();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendNotification() {
		int studentId=1;
		int notificationId=NotificationConstants.REJECT_REGISTRATION_NOTIFICATION;
		notificationoperation.sendNotification(notificationId, studentId);
		
	}
	@Test
	public void testGetNotificationMessage() {
		
		notificationoperation.getNotificationMessage(1);
		
	}
	

}
