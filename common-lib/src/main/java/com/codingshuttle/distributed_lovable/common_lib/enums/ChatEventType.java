package com.codingshuttle.distributed_lovable.common_lib.enums;

public enum ChatEventType {
    THOUGHT,      // "Thought for 2s"
    MESSAGE,      // Standard conversational text
    FILE_EDIT,    // Code generation <file>
    TOOL_LOG      // "Reading file..." <tool>
}
