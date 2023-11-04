package com.gaseng.chat.domain;

import com.gaseng.global.common.BaseTimeEntity;
import com.gaseng.member.domain.Member;
import com.gaseng.sharehouse.domain.Sharehouse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "chat_room")
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shr_id", nullable = false)
    private Sharehouse sharehouse;

    private ChatRoomStatus chatRoomStatus;

    private String message;

    @Builder
    public ChatRoom(Member sender, Member receiver, Sharehouse sharehouse) {
        this.sender = sender;
        this.receiver = receiver;
        this.sharehouse = sharehouse;
        this.chatRoomStatus = ChatRoomStatus.ACTIVE;
    }

    public static ChatRoom create(Member sender, Member receiver, Sharehouse sharehouse) {
        return new ChatRoom(sender, receiver, sharehouse);
    }

    public void toInactive() {
        this.chatRoomStatus = ChatRoomStatus.INACTIVE;
    }

    public void updateMessage(String message) {
        this.message = message;
    }
}
