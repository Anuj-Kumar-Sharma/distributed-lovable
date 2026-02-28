package com.codingshuttle.distributed_lovable.intelligence_service.repository;

import com.codingshuttle.distributed_lovable.intelligence_service.entity.ChatSession;
import com.codingshuttle.distributed_lovable.intelligence_service.entity.ChatSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, ChatSessionId> {
}
