package com.gaseng.kyc.dto;

import com.gaseng.kyc.domain.KycNoticeStatus;

public record KycNotificationResponse(
		KycNoticeStatus status,
		String description,
		String createdDate
) {

}
