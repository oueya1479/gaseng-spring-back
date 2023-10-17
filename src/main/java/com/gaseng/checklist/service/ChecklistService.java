package com.gaseng.checklist.service;

import com.gaseng.checklist.domain.Checklist;
<<<<<<< Updated upstream
import com.gaseng.checklist.Repository.ChecklistRepository;
import org.springframework.transaction.annotation.Transactional;

=======
import com.gaseng.checklist.repository.ChecklistRepository;
import com.gaseng.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
>>>>>>> Stashed changes
@Transactional
public class ChecklistService {
    private final ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }


    public Long join(Checklist checklist) {
<<<<<<< Updated upstream
        checklistRepository.create(checklist);
        return checklist.getChkId();
    }

    public Checklist search(Long mem_id) {
        return checklistRepository.search(mem_id);
    }


    public void updateChecklist(Long mem_id, Checklist updatedChecklist) {
        Checklist checklist = search(mem_id);
        // 업데이트 메서드 호출
        checklist.update(updatedChecklist);
        checklistRepository.create(checklist);
=======
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
>>>>>>> Stashed changes
    }
}
