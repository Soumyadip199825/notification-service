package com.soumyadip.notificationservice.mapper;

import com.soumyadip.notificationservice.dto.NotificationResponse;
import com.soumyadip.notificationservice.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationResponse toResponse(
            Notification notification
    );
}
