package com.gaseng.sharehouse.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaseng.file.domain.File;
import com.gaseng.file.service.FileService;
import com.gaseng.member.repository.MemberRepository;
import com.gaseng.sharehouse.domain.Sharehouse;
import com.gaseng.sharehouse.repository.SharehouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SharehouseImageService {
	private final FileService fileService;
    private final S3Uploader s3Uploader;
    
    private static final String IMAGE_PATH = "sharehouse";

	public void uploadS3Poster(MultipartFile f, Sharehouse sharehouse) throws IOException {
    	String path = s3Uploader.upload(f, IMAGE_PATH);
    	File file = File.builder()
    			.member(sharehouse.getMember())
    			.filePath(path)
    			.build();
    	
    	sharehouse.updatePoster(path);
    }

	public void uploadS3Images(List<MultipartFile> files, Sharehouse sharehouse) throws IOException {
    	for (MultipartFile f : files) {
    		String path = s3Uploader.upload(f, IMAGE_PATH);
        	File file = File.builder()
        			.member(sharehouse.getMember())
        			.filePath(path)
        			.build();
        	fileService.saveFile(file, sharehouse);
    	}
    }

	public void deleteS3Images(String file){
			s3Uploader.deleteS3(file);
	}
}
