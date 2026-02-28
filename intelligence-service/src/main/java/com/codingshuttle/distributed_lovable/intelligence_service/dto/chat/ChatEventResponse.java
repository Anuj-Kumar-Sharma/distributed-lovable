package com.codingshuttle.distributed_lovable.intelligence_service.dto.chat;


import com.codingshuttle.distributed_lovable.common_lib.enums.ChatEventType;

public record ChatEventResponse(
        Long id,
        ChatEventType type,
        Integer sequenceOrder,
        String content,
        String filePath,
        String metadata
) {
}
