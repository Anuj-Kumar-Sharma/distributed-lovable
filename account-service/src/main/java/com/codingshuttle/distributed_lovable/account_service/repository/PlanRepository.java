package com.codingshuttle.distributed_lovable.account_service.repository;

import com.codingshuttle.distributed_lovable.account_service.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByStripePriceId(String id);
}
