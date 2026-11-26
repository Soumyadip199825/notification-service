package com.soumyadip.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "usersequence")
public class UserSequence {
    @Id
    private Long userId;

    private Long nextSequence;
}
