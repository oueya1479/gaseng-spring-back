package com.gaseng.sharehouse.dto;

import com.gaseng.sharehouse.domain.Sharehouse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record SharehouseRequest(
        List<MultipartFile> files,
        String shrTitle,
        String shrDescription,
        String shrAddress,
        String shrAddressDetail
){
    public Sharehouse toSharehouse() {
    return Sharehouse.builder()
            .shrTitle(shrTitle)
            .shrDescription(shrDescription)
            .shrAddress(shrAddress)
            .shrAddressDetail(shrAddressDetail)
            .build();
}

}