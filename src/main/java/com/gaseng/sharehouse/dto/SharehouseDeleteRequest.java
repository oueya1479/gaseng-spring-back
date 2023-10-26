package com.gaseng.sharehouse.dto;

import com.gaseng.sharehouse.domain.Sharehouse;

public record SharehouseDeleteRequest (
        Long id
) {
    public Sharehouse deleteSharehouse() {
        return Sharehouse.builder()
                .build();
    }
}