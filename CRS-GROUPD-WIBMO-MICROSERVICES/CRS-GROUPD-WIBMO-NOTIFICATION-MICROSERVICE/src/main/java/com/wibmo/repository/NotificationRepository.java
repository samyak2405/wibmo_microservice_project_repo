/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.Notification;

/**
 * Notification repository related to Notification CRUD operations
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    /**
     * 
     * @param notificationMessage
     * @return Notification
     */
	public Notification findByNotificationMessage(String notificationMessage);

}
