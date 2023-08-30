/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Notification;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer> {

	public Notification findByNotificationMessage(String notificationMessage);
}
