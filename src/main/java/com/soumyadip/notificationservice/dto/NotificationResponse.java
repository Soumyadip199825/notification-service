package com.soumyadip.notificationservice.dto;

import com.soumyadip.notificationservice.enums.NotificationStatus;
import com.soumyadip.notificationservice.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;

    private Long sequenceNumber;

    private NotificationType eventType;

    private String title;

    private String message;

    private NotificationStatus status;

    private LocalDateTime createdAt;
}

