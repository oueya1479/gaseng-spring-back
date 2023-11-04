package com.gaseng.chat.dto;

public record ChatRoomListResponse(
        Long chatRoomId,
        String partnerNickname,
        String message,
        String updatedTime
) {
}
