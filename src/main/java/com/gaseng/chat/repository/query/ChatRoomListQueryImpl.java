package com.gaseng.chat.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gaseng.chat.domain.QChatRoom.chatRoom;
import static com.gaseng.chat.domain.QMemberChatRoom.memberChatRoom;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomListQueryImpl implements ChatRoomListQuery {
    private final JPAQueryFactory query;

    @Override
    public List<ChatRoomListQueryProjection> findChatRoomsByMemId(Long memId) {
        return query
                .selectDistinct(new QChatRoomListQueryProjection(
                        memberChatRoom.chatRoom.chatRoomId,
                        memberChatRoom.chatRoom.receiver.memNickname,
                        memberChatRoom.chatRoom.sender.memNickname,
                        memberChatRoom.chatRoom.message,
                        memberChatRoom.chatRoom.modifiedDate))
                .from(memberChatRoom)
                .innerJoin(chatRoom).on(memberChatRoom.chatRoom.chatRoomId.eq(chatRoom.chatRoomId))
                .where(memberChatRoom.member.memId.eq(memId))
                .orderBy(memberChatRoom.chatRoom.modifiedDate.desc())
                .fetch();
    }
}
