package com.gaseng.sharehouse.dto;

import com.gaseng.sharehouse.domain.Sharehouse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record SharehouseUpdateRequest (
		Long id,
		String shrTitle,
		String shrDescription,
		String shrAddress,
		String shrAddressDetail,
		List<MultipartFile> files
) {
	public Sharehouse updateSharehouse() {
		return Sharehouse.builder()
				.shrTitle(shrTitle)
				.shrDescription(shrDescription)
				.shrAddress(shrAddress)
				.shrAddressDetail(shrAddressDetail)
				.build();
	}
}
