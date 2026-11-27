package com.soumyadip.notificationservice.repository;

import com.soumyadip.notificationservice.entity.Notification;
import com.soumyadip.notificationservice.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

//    To retrieve all the notifications for a particular userId and notificationstatus
    Page<Notification> findByUserIdAndStatus(
            Long userId,
            NotificationStatus status,
            Pageable pageable
    );
    Page<Notification> findByUserId(
            Long userId,
            Pageable pageable
    );

    @Modifying
    @Query("""
       UPDATE Notification n
       SET n.status = 'READ'
       WHERE n.userId = :userId
       AND n.status = 'UNREAD'
       """)
    int markAllAsRead(
            Long userId,
            NotificationStatus status
    );

//    To retrieve all the unread messages for an userId
    long countByUserIdAndStatus(
            Long userId,
            NotificationStatus status);
}
