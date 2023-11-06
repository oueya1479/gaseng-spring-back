package com.gaseng.chat.repository;

import com.gaseng.chat.domain.MemberChatRoom;
import com.gaseng.chat.repository.query.ChatRoomListQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberChatRoomRepository extends JpaRepository<MemberChatRoom, Long>, ChatRoomListQuery {
    Optional<MemberChatRoom> findByMemberMemIdAndChatRoomChatRoomId(Long memId, Long chatRoomId);
}
