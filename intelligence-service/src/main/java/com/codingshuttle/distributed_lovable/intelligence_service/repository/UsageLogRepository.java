package com.codingshuttle.distributed_lovable.intelligence_service.repository;

import com.codingshuttle.distributed_lovable.intelligence_service.entity.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    Optional<UsageLog> findByUserIdAndDate(Long userId, LocalDate today);
}
