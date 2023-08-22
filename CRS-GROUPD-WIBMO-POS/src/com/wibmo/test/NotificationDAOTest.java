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
    NotificationDAO notificationdao=null;
	@Before
	public void setUp() throws Exception {
		
		notificationdao=NotificationDAOImpl.getInstance();
		notification=new Notification();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGetNotificationMessage() {
		
		List<Notification> notif = new ArrayList<>();
		notification.setId(2);
		
		notification.setNotificationMessage("Registration Rejected!! Register Again");
		
		notif.add(notification);
            
		List<Notification> notifs = notificationdao.getNotificationMessage(1);
		
		assertEquals(notif.get(0).getNotificationMessage(),notifs.get(0).getNotificationMessage());
		
		
	}


}
