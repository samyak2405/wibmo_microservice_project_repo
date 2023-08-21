package com.wibmo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wibmo.bean.Notification;
import com.wibmo.business.NotificationOperation;
import com.wibmo.business.NotificationOperationImpl;
import com.wibmo.dao.NotificationDAO;
import com.wibmo.dao.NotificationDAOImpl;

public class NotificationDAOTest {
	Notification notification =null;
    NotificationDAO notificationoperation=null;
	@Before
	public void setUp() throws Exception {
		
		notificationoperation=NotificationDAOImpl.getInstance();
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
		List<Notification> notif = new ArrayList<>();
		
		notification.setNotificationMessage("payment succesful");
		
		notif.add(notification);
            
		List<Notification> notifs = notificationoperation.getNotificationMessage(notification.getId());
		
		assertEquals(notif,notifs);
		
		
	}


}
