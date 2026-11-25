package com.soumyadip.notificationservice.entity;

import com.soumyadip.notificationservice.enums.NotificationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    private String title;

    private String messages;

    private LocalDateTime createdAt;
}
