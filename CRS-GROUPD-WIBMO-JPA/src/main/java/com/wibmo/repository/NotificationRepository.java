/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.constant.SQLConstants;
import com.wibmo.entity.Notification;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface NotificationRepository extends CrudRepository<User,Integer> {

<<<<<<< HEAD
	@Query(value=SQLConstants.SEND_NOTIFICATION, nativeQuery = true)
	void sendNotificationMessage(@Param("notifId") int notifid, @Param("studentId") long studentId);

	@Query(value=SQLConstants.GET_NOTIFICATION, nativeQuery = true)
	List<Notification> getNotificationMessage(@Param("studentId")long studentId);
=======
	@Query(value=SQLConstants.SEND_NOTIFICATION,nativeQuery=true)
	void sendNotificationMessage(int notifid, long studentId);

	@Query(value=SQLConstants.GET_NOTIFICATION,nativeQuery=true)
	List<Notification> getNotificationMessage(long studentId);
>>>>>>> 9cb10973967d01aca71b27f45019db46f17b7542
	
	

}
