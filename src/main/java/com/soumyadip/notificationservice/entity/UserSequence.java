package com.soumyadip.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usersequence")
public class UserSequence {
    @Id
    private Long userId;

    private Long sequenceNumber;
}
