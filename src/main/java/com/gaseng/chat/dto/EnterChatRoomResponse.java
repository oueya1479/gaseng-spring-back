package com.gaseng.chat.dto;

public record EnterChatRoomResponse(
        String senderNickname,
        String receiverNickname,
        String chatRoomStatus
) {
}
