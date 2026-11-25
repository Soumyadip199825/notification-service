package com.soumyadip.notificationservice.service;

import com.soumyadip.notificationservice.dto.CreateNotificationRequest;
import com.soumyadip.notificationservice.dto.NotificationResponse;
import com.soumyadip.notificationservice.dto.UnreadCountResponse;
import com.soumyadip.notificationservice.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    NotificationResponse createNotification(
            CreateNotificationRequest request);

    Page<NotificationResponse> getNotifications(
            Long userId,
            Pageable pageable);

    Page<NotificationResponse> getNotificationsByStatus(
            Long userId,
            NotificationStatus status,
            Pageable pageable);

    UnreadCountResponse getUnreadCount(
            Long userId);

    void markAsRead(
            Long notificationId);

    void markAllAsRead(
            Long userId);
}
