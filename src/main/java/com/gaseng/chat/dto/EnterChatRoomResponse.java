package com.gaseng.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record EnterChatRoomResponse(
        @Schema(description = "sender nickname", example = "닉네임1")
        String senderNickname,

        @Schema(description = "receiver nickname", example = "닉네임2")
        String receiverNickname,

        @Schema(description = "chatroom status", example = "활성/비활성")
        String chatRoomStatus
) {
}
