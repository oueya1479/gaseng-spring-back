package com.gaseng.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {
    NORMAL("노멀"),
    WAITING("대기"),
    REJECT("거절"),
    APPROVE("승인"),
    ;

    private String value;
}
