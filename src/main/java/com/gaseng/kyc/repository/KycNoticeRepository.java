package com.gaseng.kyc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaseng.kyc.domain.KycNotice;
import com.gaseng.member.domain.Member;

public interface KycNoticeRepository extends JpaRepository<KycNotice, Long> {

	Optional<List<KycNotice>> findByMember(Member member);

}
