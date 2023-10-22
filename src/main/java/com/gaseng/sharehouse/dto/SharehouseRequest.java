package com.gaseng.sharehouse.dto;


import com.gaseng.member.domain.Member;
import com.gaseng.sharehouse.domain.Sharehouse;
import com.gaseng.sharehouse.domain.SharehouseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record SharehouseRequest(
        Member member,
        List<MultipartFile> file,
        String shrTitle,
        String shrDescription,
        String shrAddress,
        String shrAddressDetail,
        String shrLatitude,
        String shrLongitude,
        String shrPoster,
        SharehouseStatus shrStatus
){
    public Sharehouse toSharehouse() {
    return Sharehouse.builder()
            .member(member)
            .shrTitle(shrTitle)
            .shrDescription(shrDescription)
            .shrAddress(shrAddress)
            .shrAddressDetail(shrAddressDetail)
            .shrLatitude(shrLatitude)
            .shrLongitude(shrLongitude)
            .shrPoster(shrPoster)
            .shrStatus(shrStatus)
            .build();
}

}