package com.gaseng.checklist.service;

import com.gaseng.checklist.domain.Checklist;
import com.gaseng.checklist.dto.ChecklistResponse;
import com.gaseng.checklist.repository.ChecklistRepository;
import com.gaseng.member.domain.Member;
import com.gaseng.member.repository.MemberRepository;
import com.gaseng.member.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final MemberRepository memberRepository;
    private final MemberInfoService memberInfoService;

    public Long create(Long memId, Checklist checklist) {
        Member member = memberInfoService.findByMemId(memId);

        checklist = Checklist.builder()
                .member(member)
                .chkSleepingHabit(checklist.getChkSleepingHabit())
                .chkCigarette(checklist.getChkCigarette())
                .chkSleepTime(checklist.getChkSleepTime())
                .chkMbti(checklist.getChkMbti())
                .chkCallPlace(checklist.getChkCallPlace())
                .chkType(checklist.getChkType())
                .build();
        checklistRepository.save(checklist);

        return checklist.getChkId();
    }

    public Long updateChecklist(Long memId,Checklist updateChecklist) {
        Optional<Member> member = memberRepository.findByMemId(memId);
        Checklist checklist = checklistRepository.findByMember(member.get());
        checklist.update(updateChecklist);
        checklistRepository.save(checklist);
        return checklist.getChkId();
    }

	public ChecklistResponse get(Long memId) {
		Optional<Member> member = memberRepository.findByMemId(memId);
		Checklist checklist = checklistRepository.findByMember(member.get());
		return new ChecklistResponse(
			    checklist.getChkId(),
			    checklist.getChkSleepingHabit(),
			    checklist.getChkCigarette(),
			    checklist.getChkSleepTime(),
			    checklist.getChkMbti(),
			    checklist.getChkCallPlace(),
			    checklist.getChkType()
		);
	}
}
