package com.gaseng.sharehouse.dto;

import java.util.List;

import com.gaseng.member.domain.Member;

public record SharehouseResponse (
		String title,
		String description,
		String address,
		String addressDetail,
		String poster,
		List<String> images
) {

}
