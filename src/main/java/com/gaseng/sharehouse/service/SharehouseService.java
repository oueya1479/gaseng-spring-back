package com.gaseng.sharehouse.service;


import com.gaseng.member.domain.Member;
import com.gaseng.member.repository.MemberRepository;
import com.gaseng.sharehouse.domain.Sharehouse;
import com.gaseng.sharehouse.repository.SharehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SharehouseService {
    private final SharehouseRepository sharehouseRepository;
    private final MemberRepository memberRepository;
    @Autowired
    private S3Uploader s3Uploader;
    public Long join(Long memId, MultipartFile file, Sharehouse sharehouse) throws IOException {
        Optional<Member> member = memberRepository.findByMemId(memId);
        sharehouse = Sharehouse.builder()
                .member(member.get())
                .shrTitle(sharehouse.getShrTitle())
                .shrDescription(sharehouse.getShrDescription())
                .shrAddress(sharehouse.getShrAddress())
                .shrAddressDetail(sharehouse.getShrAddressDetail())
                .shrLatitude(sharehouse.getShrLatitude())
                .shrLongitude(sharehouse.getShrLongitude())
                .shrPoster(sharehouse.getShrPoster())
                .shrStatus(sharehouse.getShrStatus())
                .build();


        s3Uploader.upload(file,"file");
        //file.setFilePath(storedFileName);
        sharehouseRepository.save(sharehouse);
        return sharehouse.getShrId();
    }

    public void updateChecklist(Long memId,Sharehouse updateSharehouse) {
        Optional<Member> member = memberRepository.findByMemId(memId);
        Sharehouse sharehouse = sharehouseRepository.findByMember(member.get());
        sharehouse.update(updateSharehouse);
        sharehouseRepository.save(sharehouse);
    }


}
