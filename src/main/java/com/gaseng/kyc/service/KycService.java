package com.gaseng.kyc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaseng.global.exception.BaseException;
import com.gaseng.global.util.LocalDateTimeFormatter;
import com.gaseng.kyc.domain.KycNotice;
import com.gaseng.kyc.domain.KycRequire;
import com.gaseng.kyc.dto.KycNotificationResponse;
import com.gaseng.kyc.dto.KycSubmitRequest;
import com.gaseng.kyc.exception.KycErrorCode;
import com.gaseng.kyc.repository.KycNoticeRepository;
import com.gaseng.kyc.repository.KycRequireRepository;
import com.gaseng.member.domain.Member;
import com.gaseng.member.domain.MemberStatus;
import com.gaseng.member.service.MemberFindService;
import com.gaseng.sharehouse.service.S3Uploader;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KycService {
	private final MemberFindService memberfindService;
	private final KycRequireRepository kycRequireRepository;
	private final KycNoticeRepository kycNoticeRepository;
	private final S3Uploader s3Uploader;
	
	private static final String IMAGE_PATH = "kyc";
	
	@Transactional
	public Long submit(Long memId, KycSubmitRequest request) throws IOException {
		
		// member 가져오기
		// member status 대기로 변경
		Member member = memberfindService.findByMemId(memId);
		member.setStatus(MemberStatus.WAITING);
		
		// 신분증, 얼굴사진 s3에 업로드
		String cardImagePath = s3Uploader.upload(request.card(), IMAGE_PATH);
		String faceImagePath = s3Uploader.upload(request.face(), IMAGE_PATH);
		
		KycRequire entity = request.toKycRequire(member, cardImagePath, faceImagePath);
		return kycRequireRepository.save(entity).getKycrId();
	}

	public List<KycNotificationResponse> getNotification(Long memId) {
		
		Member member = memberfindService.findByMemId(memId);
		List<KycNotice> entity = kycNoticeRepository.findByMember(member)
				.orElseThrow(() -> BaseException.type(KycErrorCode.KYC_NOTICE_NOT_FOUND));
		
		List<KycNotificationResponse> list = new ArrayList<KycNotificationResponse>();
		for (KycNotice en : entity) {
			list.add(new KycNotificationResponse(
					en.getKycnStatus(), 
					en.getKycnDescription(), 
					LocalDateTimeFormatter.formatTimeDifference(en.getCreatedDate()
			)));
		}
		return list;
	}
	
}
