package com.codingshuttle.distributed_lovable.intelligence_service.service.impl;

import com.codingshuttle.distributed_lovable.common_lib.security.AuthUtil;
import com.codingshuttle.distributed_lovable.intelligence_service.dto.chat.ChatResponse;
import com.codingshuttle.distributed_lovable.intelligence_service.entity.ChatMessage;
import com.codingshuttle.distributed_lovable.intelligence_service.entity.ChatSession;
import com.codingshuttle.distributed_lovable.intelligence_service.entity.ChatSessionId;
import com.codingshuttle.distributed_lovable.intelligence_service.mapper.ChatMapper;
import com.codingshuttle.distributed_lovable.intelligence_service.repository.ChatMessageRepository;
import com.codingshuttle.distributed_lovable.intelligence_service.repository.ChatSessionRepository;
import com.codingshuttle.distributed_lovable.intelligence_service.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatSessionRepository chatSessionRepository;
    private final AuthUtil authUtil;
    private final ChatMapper chatMapper;

    @Override
    public List<ChatResponse> getProjectChatHistory(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        ChatSession chatSession = chatSessionRepository.getReferenceById(
                new ChatSessionId(projectId, userId)
        );

        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatSession(chatSession);

        return chatMapper.fromListOfChatMessage(chatMessageList);
    }
}
