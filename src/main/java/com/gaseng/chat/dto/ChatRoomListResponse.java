package com.gaseng.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ChatRoomListResponse(
        @Schema(description = "chat_room id (index)", example = "1")
        Long chatRoomId,

        @Schema(description = "chat partner nickname", example = "상대방 닉네임")
        String partnerNickname,

        @Schema(description = "last message content", example = "hello")
        String message,

        @Schema(description = "last message update time",
                example = "지금/n분 전/n시간 전/n일 전/n달 전/n년 전")
        String updatedTime
) {
}
