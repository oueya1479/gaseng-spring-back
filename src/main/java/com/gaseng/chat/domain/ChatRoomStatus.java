package com.gaseng.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatRoomStatus {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    ;

    private String value;
}
