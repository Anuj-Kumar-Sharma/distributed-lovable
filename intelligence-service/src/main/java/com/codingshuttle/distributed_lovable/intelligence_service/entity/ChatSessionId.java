package com.codingshuttle.distributed_lovable.intelligence_service.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class ChatSessionId implements Serializable {
    Long projectId;
    Long userId;
}
