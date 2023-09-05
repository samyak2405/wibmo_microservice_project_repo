package com.wibmo.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wibmo.entity.*;
import com.wibmo.exception.UserNotApprovedException;
import com.wibmo.service.*;




@ExtendWith(MockitoExtension.class)

public class TestNotificationDAO {
	@InjectMocks
	NotificationOperationImpl notificationOp;
	
	@Mock
	NotificationRepository notificationDao;
	
	@Test
	public void testFindByNotificationMessage() {
		
	    


	       Notification notification = new Notification();
	       notification.setNotificationMessage("Registration Approved");
	       when(notificationDao.findByNotificationMessage("Registration Approved")).thenReturn(notification);

	       Notification notification1=notificationDao.findByNotificationMessage("Registration Approved");

	        assertEquals(notification,notification1);
		
	}
	
	
	

}
