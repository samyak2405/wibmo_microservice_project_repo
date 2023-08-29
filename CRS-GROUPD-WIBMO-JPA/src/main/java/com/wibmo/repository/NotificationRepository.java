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
public interface NotificationRepository extends CrudRepository<User,Integer> {
	@Transactional
    @Modifying
	@Query(value="INSERT INTO notificationstudentmapping VALUES(?1,?2)", nativeQuery = true)
	void sendNotificationMessage(@Param("notifId") int notifid, @Param("studentId") long studentId);
	@Transactional
	@Query(value="SELECT * FROM notification WHERE notificationId IN (SELECT notifId FROM notificationstudentmapping WHERE studentId=?1)", nativeQuery = true)
	List<Notification> getNotificationMessage(@Param("studentId")long studentId);
	
}
