package com.gaseng.global.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class LocalDateTimeFormatter {
    public static String formatTimeDifference(LocalDateTime modifiedDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(modifiedDate, now);

        if (duration.toMinutes() < 1) {
            return "지금";
        } else if (duration.toMinutes() < 60) {
            return duration.toMinutes() + "분 전";
        } else if (duration.toHours() < 24) {
            return duration.toHours() + "시간 전";
        } else if (duration.toDays() < 30) {
            return duration.toDays() + "일 전";
        } else if (duration.toDays() < 365) {
            return duration.toDays() / 30 + "달 전";
        } else {
            return duration.toDays() / 365 + "년 전";
        }
    }
}
