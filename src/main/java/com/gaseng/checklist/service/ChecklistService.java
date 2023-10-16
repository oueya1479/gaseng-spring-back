package com.gaseng.checklist.service;

import com.gaseng.checklist.domain.Checklist;
import com.gaseng.checklist.Repository.ChecklistRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ChecklistService {
    private final ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }


    public Long join(Checklist checklist) {
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
    }
}
