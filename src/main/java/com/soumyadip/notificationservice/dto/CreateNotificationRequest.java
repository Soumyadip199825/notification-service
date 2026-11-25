package com.soumyadip.notificationservice.dto;

import com.soumyadip.notificationservice.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationRequest {

    private Long userId;

    private NotificationType eventType;

    private String title;

    private String message;
}
