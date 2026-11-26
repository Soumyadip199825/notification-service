package com.soumyadip.notificationservice.repository;

import com.soumyadip.notificationservice.entity.UserSequence;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserSequenceRepository extends JpaRepository<UserSequence, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
       select us
       from UserSequence us
       where us.userId = :userId
       """)
    Optional<UserSequence> findForUpdate(
            Long userId
    );

}
