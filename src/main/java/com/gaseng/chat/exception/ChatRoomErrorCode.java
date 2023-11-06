package com.gaseng.chat.exception;

import com.gaseng.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatRoomErrorCode implements ErrorCode {
    DUPLICATE_CHAT_ROOM(HttpStatus.CONFLICT, "CHATROOM_001", "이미 생성된 채팅방입니다."),
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "CHATROOM_002", "채팅방을 찾을 수 없습니다."),
    INACTIVE_CHAT_ROOM(HttpStatus.BAD_REQUEST, "CHATROOM_003", "채팅이 불가능한 채팅방입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
