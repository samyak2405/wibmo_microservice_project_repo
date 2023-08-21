package com.wibmo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.bean.Notification;
import com.wibmo.business.NotificationOperation;
import com.wibmo.business.NotificationOperationImpl;

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
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetNotificationMessage() {
		notification.setId(1);
		notification.setNotificationMessage("");
		
	}
	@Test
	public void testSendNotification() {
		notification.setId(1);
		notification.setNotificationMessage("");
	}

}
