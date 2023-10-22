package com.gaseng.file.service;

import com.gaseng.file.domain.File;
import com.gaseng.file.repository.FileRepository;
import com.gaseng.member.domain.Member;
import com.gaseng.member.repository.MemberRepository;
import com.gaseng.sharehouse.domain.Sharehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

}
