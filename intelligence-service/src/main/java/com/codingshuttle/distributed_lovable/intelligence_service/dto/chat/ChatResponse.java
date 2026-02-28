package com.codingshuttle.distributed_lovable.intelligence_service.dto.chat;


import com.codingshuttle.distributed_lovable.common_lib.enums.MessageRole;

import java.time.Instant;
import java.util.List;

public record ChatResponse(
        Long id,
        MessageRole role,
        List<ChatEventResponse> events,
        String content,
        Integer tokensUsed,
        Instant createdAt

) {
}
