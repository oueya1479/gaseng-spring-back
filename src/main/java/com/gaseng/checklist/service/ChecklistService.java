package com.gaseng.checklist.service;

import com.gaseng.checklist.domain.Checklist;
import com.gaseng.checklist.repository.ChecklistRepository;
import com.gaseng.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChecklistService {
    private final ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }


    public Long join(Checklist checklist) {
        checklistRepository.save(checklist);
        return checklist.getChkId();
    }

    public Checklist search(Member member) {
        return checklistRepository.findByMember(member);
    }
    public void updateChecklist(Member member,Checklist updateChecklist) {
        Checklist checklist = search(member);
        checklist.update(updateChecklist);
        checklistRepository.save(checklist);
    }
}
