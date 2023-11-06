package com.gaseng.chat.repository.query;

import java.util.List;

public interface ChatRoomListQuery {
    List<ChatRoomListQueryProjection> findChatRoomsByMemId(Long memId);
}
