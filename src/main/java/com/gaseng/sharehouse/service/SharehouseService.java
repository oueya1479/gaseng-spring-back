package com.gaseng.sharehouse.service;

import com.gaseng.file.domain.File;
import com.gaseng.file.domain.ShareFile;
import com.gaseng.file.repository.FileRepository;
import com.gaseng.file.repository.ShareFileRepository;
import com.gaseng.global.exception.BaseException;
import com.gaseng.member.domain.Member;
import com.gaseng.member.repository.MemberRepository;
import com.gaseng.sharehouse.domain.Sharehouse;
import com.gaseng.sharehouse.domain.SharehouseStatus;
import com.gaseng.sharehouse.dto.SharehouseRequest;
import com.gaseng.sharehouse.dto.SharehouseResponse;
import com.gaseng.sharehouse.dto.SharehouseUpdateRequest;
import com.gaseng.sharehouse.exception.SharehouseErrorCode;
import com.gaseng.sharehouse.repository.SharehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
@Transactional
@RequiredArgsConstructor
public class SharehouseService {
    private final SharehouseRepository sharehouseRepository;
    private final MemberRepository memberRepository;
    private final SharehouseImageService sharehouseImageService;
	private final FileRepository fileRepository;

    public SharehouseResponse get(Long shrId) {
    	Optional<Sharehouse> sharehouse = sharehouseRepository.findByShrId(shrId);
    	Sharehouse sharehouseEntity = sharehouse.get();
    	
    	List<String> paths = new ArrayList<>();
    	for (ShareFile shareFile : sharehouseEntity.getShareFiles()) {
    		paths.add(shareFile.getFile().getFilePath());
    	}
    	
		return new SharehouseResponse(
				sharehouseEntity.getShrTitle(), 
				sharehouseEntity.getShrDescription(), 
				sharehouseEntity.getShrAddress(), 
				sharehouseEntity.getShrAddressDetail(), 
				sharehouseEntity.getShrPoster(), 
				paths
		);
	}
    
    public List<SharehouseResponse> getAll() {
    	List<SharehouseResponse> responses = new ArrayList<>();
    	List<Sharehouse> sharehouses = sharehouseRepository.findAll();
    	
    	for (Sharehouse sharehouse : sharehouses) {
    		List<String> paths = new ArrayList<>();
        	for (ShareFile shareFile : sharehouse.getShareFiles()) {
        		paths.add(shareFile.getFile().getFilePath());
        	}
        	
        	responses.add(new SharehouseResponse(
        			sharehouse.getShrTitle(), 
        			sharehouse.getShrDescription(), 
        			sharehouse.getShrAddress(), 
        			sharehouse.getShrAddressDetail(), 
        			sharehouse.getShrPoster(), 
    				paths
        	));
        	
    	}
    	
		return responses;
	}
    
    public Long create(Long memId, MultipartFile poster, SharehouseRequest request) throws IOException {
        Optional<Member> member = memberRepository.findByMemId(memId);
        
        Sharehouse sharehouse = Sharehouse.builder()
        		.member(member.get())
                .shrTitle(request.shrTitle())
                .shrDescription(request.shrDescription())
                .shrAddress(request.shrAddress())
                .shrAddressDetail(request.shrAddressDetail())
                .shrPoster("")
                .shrStatus(SharehouseStatus.ENABLE)
                .build();

        Sharehouse savedSharehouse = sharehouseRepository.save(sharehouse);
        this.processImage(poster, request.files(), savedSharehouse);
        
        return sharehouse.getShrId();
    }
    
    public Long update(Long memId,SharehouseUpdateRequest request) {
    	Optional<Sharehouse> sharehouse = sharehouseRepository.findByShrId(request.id());
    	Sharehouse sharehouseEntity = sharehouse.get();
    	this.isAuthor(memId, sharehouseEntity.getMember().getMemId());
    	sharehouseEntity.update(request.shrTitle(), request.shrDescription(),request.shrAddress(), request.shrAddressDetail());
    	sharehouseRepository.save(sharehouseEntity);
		return sharehouseEntity.getShrId();
	}
    
    private void isAuthor(Long memId, Long author) {
    	if (memId != author) {
    		throw BaseException.type(SharehouseErrorCode.USER_ID_MISMATCH);
    	}
    }

    private void processImage(
    		MultipartFile poster, 
    		List<MultipartFile> files,
    		Sharehouse sharehouse
    ) throws IOException {
    	sharehouseImageService.uploadS3Poster(poster, sharehouse);
    	sharehouseImageService.uploadS3Images(files, sharehouse);
    }

	public void deleteSharehouse(Long memId, Long shrId){
		Optional<Sharehouse> sharehouse = sharehouseRepository.findById(shrId);
		isAuthor(memId,sharehouse.get().getMember().getMemId());
		List<File> files = fileRepository.findByMember(memberRepository.findByMemId(memId).get());
		for (File file : files) {
			sharehouseImageService.deleteS3Images("sharehouse/"+file.getFilePath().toString().split("/")[4]);
		}

		sharehouseRepository.delete(sharehouse.get());
	}
    
}
