package com.gaseng.chat.service;

import com.gaseng.chat.domain.ChatRoom;
import com.gaseng.chat.domain.ChatRoomStatus;
import com.gaseng.chat.domain.MemberChatRoom;
import com.gaseng.chat.dto.ChatRoomCreateResponse;
import com.gaseng.chat.dto.ChatRoomEnterResponse;
import com.gaseng.chat.exception.ChatRoomErrorCode;
import com.gaseng.chat.repository.ChatRoomRepository;
import com.gaseng.chat.repository.MemberChatRoomRepository;
import com.gaseng.global.exception.BaseException;
import com.gaseng.member.domain.Member;
import com.gaseng.member.service.MemberInfoService;
import com.gaseng.sharehouse.domain.Sharehouse;
import com.gaseng.sharehouse.repository.SharehouseRepository;
import com.gaseng.sharehouse.service.SharehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final SharehouseService sharehouseService;
    private final SharehouseRepository sharehouseRepository;
    private final MemberInfoService memberInfoService;
    private final MemberChatRoomRepository memberChatRoomRepository;

    @Transactional
    public ChatRoomCreateResponse createChatRoom(Long memId, Long shrId) {
        Member sender = memberInfoService.findByMemId(memId);
        Sharehouse sharehouse = sharehouseService.findSharehouseByShrId(shrId);
        Member receiver = sharehouse.getMember();

        validateDuplicateChatRoom(sender, receiver, sharehouse);
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.create(sender, receiver, sharehouse));

        memberChatRoomRepository.save(MemberChatRoom.create(chatRoom, sender));
        memberChatRoomRepository.save(MemberChatRoom.create(chatRoom, receiver));

        return new ChatRoomCreateResponse(
                chatRoom.getChatRoomId(),
                chatRoom.getSender().getMemId(),
                chatRoom.getReceiver().getMemId()
        );
    }

    public ChatRoomEnterResponse enterChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = findByChatRoomId(chatRoomId);

        return new ChatRoomEnterResponse(
                chatRoom.getSender().getMemNickname(),
                chatRoom.getReceiver().getMemNickname(),
                chatRoom.getChatRoomStatus().getValue()
        );
    }

    @Transactional
    public Long updateMessage(Long chatRoomId, String message) {
        ChatRoom chatRoom = findByChatRoomId(chatRoomId);
        validateActiveChatRoom(chatRoom);
        chatRoom.updateMessage(message);

        return chatRoomId;
    }

    @Transactional
    public Long deleteChatRoom(Long memId, Long chatRoomId) {
        MemberChatRoom memberChatRoom = findByMemIdAndChatRoomId(memId, chatRoomId);
        ChatRoom chatRoom = findByChatRoomId(chatRoomId);

        chatRoom.toInactive();
        memberChatRoomRepository.delete(memberChatRoom);

        return chatRoomId;
    }

    public ChatRoom findByChatRoomId(Long chatRoomId) {
        return chatRoomRepository.findByChatRoomId(chatRoomId)
                .orElseThrow(() -> BaseException.type(ChatRoomErrorCode.CHAT_ROOM_NOT_FOUND));
    }

    public MemberChatRoom findByMemIdAndChatRoomId(Long memId, Long chatRoomId) {
        return memberChatRoomRepository.findByMemberMemIdAndChatRoomChatRoomId(memId, chatRoomId)
                .orElseThrow(() -> BaseException.type(ChatRoomErrorCode.CHAT_ROOM_NOT_FOUND));
    }

    private void validateDuplicateChatRoom(Member sender, Member receiver, Sharehouse sharehouse) {
        if (chatRoomRepository.existsBySenderAndReceiverAndSharehouse(sender, receiver, sharehouse)) {
            throw BaseException.type(ChatRoomErrorCode.DUPLICATE_CHAT_ROOM);
        }
    }

    private void validateActiveChatRoom(ChatRoom chatRoom) {
        if (chatRoom.getChatRoomStatus().equals(ChatRoomStatus.INACTIVE)) {
            throw BaseException.type(ChatRoomErrorCode.INACTIVE_CHAT_ROOM);
        }
    }
}
