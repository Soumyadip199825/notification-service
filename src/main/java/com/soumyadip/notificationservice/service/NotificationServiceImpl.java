package com.soumyadip.notificationservice.service;

import com.soumyadip.notificationservice.dto.UnreadCountResponse;
import com.soumyadip.notificationservice.enums.NotificationStatus;
import com.soumyadip.notificationservice.mapper.NotificationMapper;
import com.soumyadip.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    @Override
    public UnreadCountResponse getUnreadCount(
            Long userId) {

        long unreadCount =
                notificationRepository
                        .countByUserIdAndStatus(
                                userId,
                                NotificationStatus.UNREAD
                        );

        return new UnreadCountResponse(
                unreadCount
        );
    }

}
