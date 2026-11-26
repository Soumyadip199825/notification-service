package com.soumyadip.notificationservice.service;

import com.soumyadip.notificationservice.dto.CreateNotificationRequest;
import com.soumyadip.notificationservice.dto.NotificationResponse;
import com.soumyadip.notificationservice.dto.UnreadCountResponse;
import com.soumyadip.notificationservice.entity.Notification;
import com.soumyadip.notificationservice.entity.UserSequence;
import com.soumyadip.notificationservice.enums.NotificationStatus;
import com.soumyadip.notificationservice.mapper.NotificationMapper;
import com.soumyadip.notificationservice.repository.NotificationRepository;
import com.soumyadip.notificationservice.repository.UserSequenceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final UserSequenceRepository userSequenceRepository;

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

    @Transactional
    @Override
    public NotificationResponse createNotification(
            CreateNotificationRequest request) {
        long nextSequence = generateSequenceNumber(request);

        Notification notification =
                Notification.builder()
                        .userId(request.getUserId())
                        .sequenceNumber(nextSequence)
                        .eventType(request.getEventType())
                        .title(request.getTitle())
                        .message(request.getMessage())
                        .status(NotificationStatus.UNREAD)
                        .createdAt(LocalDateTime.now())
                        .build();

        Notification saved =
                notificationRepository.save(
                        notification
                );

        return notificationMapper.toResponse(saved);
    }
    Long generateSequenceNumber(CreateNotificationRequest request){
        UserSequence userSequence =
                userSequenceRepository
                        .findForUpdate(
                                request.getUserId()
                        )
                        .orElse(...);

        Long nextSequence =
                userSequence.getSequenceNumber();

        userSequence.setSequenceNumber(
                nextSequence + 1
        );
        return nextSequence;
    }

}
