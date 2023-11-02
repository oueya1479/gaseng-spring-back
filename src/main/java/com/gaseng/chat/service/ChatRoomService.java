package com.gaseng.chat.service;

import com.gaseng.chat.domain.ChatRoom;
import com.gaseng.chat.dto.ChatRoomCreateResponse;
import com.gaseng.chat.dto.ChatRoomEnterResponse;
import com.gaseng.chat.exception.ChatRoomErrorCode;
import com.gaseng.chat.repository.ChatRoomRepository;
import com.gaseng.global.exception.BaseException;
import com.gaseng.member.domain.Member;
import com.gaseng.member.service.MemberInfoService;
import com.gaseng.sharehouse.domain.Sharehouse;
import com.gaseng.sharehouse.domain.SharehouseStatus;
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

    @Transactional
    public ChatRoomCreateResponse createChatRoom(Long memId, Long shrId) {
        Member sender = memberInfoService.findByMemId(memId);
        Sharehouse sharehouse = sharehouseService.findSharehouseByShrId(shrId);

        validateDuplicateChatRoom(sender, sharehouse.getMember(), sharehouse);
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.create(sender, sharehouse.getMember(), sharehouse));

        return new ChatRoomCreateResponse(
                chatRoom.getChatRoomId(),
                chatRoom.getSender().getMemId(),
                chatRoom.getReceiver().getMemId()
        );
    }

    public ChatRoomEnterResponse enterChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = findByChatRoomId(chatRoomId);
        validateActiveChatRoom(chatRoom);

        return new ChatRoomEnterResponse(
                chatRoom.getSender().getMemNickname(),
                chatRoom.getReceiver().getMemNickname(),
                chatRoom.getChatRoomStatus().getValue()
        );
    }

    public ChatRoom findByChatRoomId(Long chatRoomId) {
        return chatRoomRepository.findByChatRoomId(chatRoomId)
                .orElseThrow(() -> BaseException.type(ChatRoomErrorCode.CHAT_ROOM_NOT_FOUND));
    }

    private void validateDuplicateChatRoom(Member sender, Member receiver, Sharehouse sharehouse) {
        if (chatRoomRepository.existsBySenderAndReceiverAndSharehouse(sender, receiver, sharehouse)) {
            throw BaseException.type(ChatRoomErrorCode.DUPLICATE_CHAT_ROOM);
        }
    }

    private void validateActiveChatRoom(ChatRoom chatRoom) {
        isExistsSharehouse(chatRoom);
        isDisableSharehouse(chatRoom);
    }

    private void isExistsSharehouse(ChatRoom chatRoom) {
        Long shrId = chatRoom.getSharehouse().getShrId();
        if (!sharehouseRepository.existsByShrId(shrId)) {
            throw BaseException.type(ChatRoomErrorCode.INACTIVE_CHAT_ROOM);
        }
    }

    private void isDisableSharehouse(ChatRoom chatRoom) {
        Sharehouse sharehouse = chatRoom.getSharehouse();
        if (sharehouse.getShrStatus().equals(SharehouseStatus.DISABLE)) {
            throw BaseException.type(ChatRoomErrorCode.INACTIVE_CHAT_ROOM);
        }
    }
}
