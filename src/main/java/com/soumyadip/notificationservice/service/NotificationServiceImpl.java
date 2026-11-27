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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        long nextSequence = generateSequenceNumber(request.getUserId());

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

//        Notification object is not in persisted context since a new object is created, hence save().
        Notification saved =
                notificationRepository.save(
                        notification
                );

        return notificationMapper.toResponse(saved);
    }
    @Override
    public Page<NotificationResponse> getNotifications(
            Long userId,
            Pageable pageable) {

        return notificationRepository
                .findByUserId(
                        userId,
                        pageable
                )
                .map(
                        notificationMapper::toResponse
                );
    }
    @Override
    public Page<NotificationResponse> getNotificationsByStatus(
            Long userId,
            NotificationStatus status,
            Pageable pageable){
        return notificationRepository
                .findByUserIdAndStatus(
                        userId,
                        status,
                        pageable
                )
                .map(
                        notificationMapper::toResponse
                );
    }

    @Transactional
    @Override
    public void markAsRead(
            Long notificationId){
        Notification notification = notificationRepository
                .findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException(
                        notificationId
                ));
        notification.setStatus(NotificationStatus.READ);
    }

    @Transactional
    @Override
    public int markAllAsRead(
            Long userId) {

        return notificationRepository
                .markAllAsRead(
                        userId,
                        NotificationStatus.READ
                );
    }
    Long generateSequenceNumber(Long userId){
        UserSequence userSequence =
                userSequenceRepository
                        .findForUpdate(
                                userId
                        )
                        .orElseGet(()->{
                            UserSequence newUserSequence = UserSequence.builder()
                                    .userId(userId)
                                    .nextSequence(1L)
                                    .build();
                            userSequenceRepository.save(newUserSequence);
                            return newUserSequence;
                        });

        Long nextSequence =
                userSequence.getNextSequence();

//        Due to dirty checking inside @Transactional, and userSequence already in persisted context hence no need of
//        save().
        userSequence.setNextSequence(
                nextSequence + 1
        );
        return nextSequence;
    }

}
