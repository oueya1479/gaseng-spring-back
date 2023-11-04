package com.gaseng.chat.repository.query;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomListQueryProjection {
    private final Long chatRoomId;
    private final String senderNickname;
    private final String receiverNickname;
    private final String message;
    private final LocalDateTime modifiedDate;

    @QueryProjection
    public ChatRoomListQueryProjection(Long chatRoomId, String senderNickname, String receiverNickname, String message, LocalDateTime modifiedDate) {
        this.chatRoomId = chatRoomId;
        this.senderNickname = senderNickname;
        this.receiverNickname = receiverNickname;
        this.message = message;
        this.modifiedDate = modifiedDate;
    }
}
