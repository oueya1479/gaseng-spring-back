package com.gaseng.file.service;

import com.gaseng.file.domain.File;
import com.gaseng.file.domain.ShareFile;
import com.gaseng.file.repository.FileRepository;
import com.gaseng.file.repository.ShareFileRepository;
import com.gaseng.member.repository.MemberRepository;
import com.gaseng.sharehouse.domain.Sharehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final ShareFileRepository shareFileRepository;
    private final MemberRepository memberRepository;
    
    @Transactional
    public File saveFile(File file) {
        return fileRepository.save(file);
    }
    
    @Transactional
    public ShareFile saveFile(File file, Sharehouse sharehouse) {
        File savedFile = this.saveFile(file);
        ShareFile shareFile = ShareFile.builder()
                .file(savedFile)
                .sharehouse(sharehouse)
                .build();
        return shareFileRepository.save(shareFile);
    }

}
