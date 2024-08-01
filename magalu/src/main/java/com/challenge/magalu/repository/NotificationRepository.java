package com.challenge.magalu.repository;

import com.challenge.magalu.entity.Notification;
import com.challenge.magalu.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.status = :status and n.dateAndTime < :dateAndTime")
    List<Notification> findNotificationByDateAndTimeBeforeAndStatus(@Param("dateAndTime") LocalDateTime dateAndTime, @Param("status") Status status);
}
