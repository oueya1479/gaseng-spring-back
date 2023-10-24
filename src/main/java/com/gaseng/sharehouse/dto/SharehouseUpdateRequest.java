package com.gaseng.sharehouse.dto;

import com.gaseng.sharehouse.domain.Sharehouse;

public record SharehouseUpdateRequest (
		Long id,
		String title,
		String description
) {
	
}
