package com.codingshuttle.distributed_lovable.intelligence_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Entity
@Table(name = "usage_logs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "date"}) // One log per user per day
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(nullable = false)
    LocalDate date;

    Integer tokensUsed;
}