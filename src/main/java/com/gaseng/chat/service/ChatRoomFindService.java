package com.gaseng.chat.service;

import com.gaseng.chat.domain.ChatRoom;
import com.gaseng.chat.domain.MemberChatRoom;
import com.gaseng.chat.exception.ChatRoomErrorCode;
import com.gaseng.chat.repository.ChatRoomRepository;
import com.gaseng.chat.repository.MemberChatRoomRepository;
import com.gaseng.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomFindService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;

    public ChatRoom findByChatRoomId(Long chatRoomId) {
        return chatRoomRepository.findByChatRoomId(chatRoomId)
                .orElseThrow(() -> BaseException.type(ChatRoomErrorCode.CHAT_ROOM_NOT_FOUND));
    }

    public MemberChatRoom findByMemIdAndChatRoomId(Long memId, Long chatRoomId) {
        return memberChatRoomRepository.findByMemberMemIdAndChatRoomChatRoomId(memId, chatRoomId)
                .orElseThrow(() -> BaseException.type(ChatRoomErrorCode.CHAT_ROOM_NOT_FOUND));
    }
}
