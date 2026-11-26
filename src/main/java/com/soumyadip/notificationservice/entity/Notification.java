package com.soumyadip.notificationservice.entity;

import com.soumyadip.notificationservice.enums.NotificationStatus;
import com.soumyadip.notificationservice.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long sequenceNumber;

    @Enumerated(EnumType.STRING)
    private NotificationType eventType;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private String title;

    private String message;

    private LocalDateTime createdAt;
}
