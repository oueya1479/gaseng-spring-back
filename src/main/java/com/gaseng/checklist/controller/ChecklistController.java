package com.gaseng.checklist.controller;

import com.gaseng.checklist.domain.Checklist;
import com.gaseng.checklist.service.ChecklistService;
import com.gaseng.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@Controller
public class ChecklistController {

    private final ChecklistService checklistService;
    private final MemberService memberService;
    @Autowired
    public ChecklistController(ChecklistService checklistService, MemberService memberService){
        this.checklistService = checklistService;
        this.memberService = memberService;
    }

    @PostMapping(value = "/checklist/create")
    public void create(@RequestBody ChecklistForm form){
        Checklist checklist = Checklist.builder()
                .chkSleepingHabit(form.getChkSleepingHabit())
                .chkCigarette(form.getChkCigarette())
                .chkSleepTime(form.getChkSleepTime())
                .chkMbti(form.getChkMbti())
                .chkCallPlace(form.getChkCallPlace())
                .chkType(form.getChkType())
                .build();
        checklistService.join(checklist);
    }

    @PostMapping(value = "/checklist/update")
    public void updateChecklist(@RequestBody ChecklistForm form) {
        // ChecklistForm에서 받아온 데이터를 Checklist 엔티티로 변환
        Checklist checklist = Checklist.builder()
                .member(form.getMember())
                .chkSleepingHabit(form.getChkSleepingHabit())
                .chkCigarette(form.getChkCigarette())
                .chkSleepTime(form.getChkSleepTime())
                .chkMbti(form.getChkMbti())
                .chkCallPlace(form.getChkCallPlace())
                .chkType(form.getChkType())
                .build();

        // 엔티티를 저장 또는 업데이트
        checklistService.updateChecklist(form.getMember().getMemId(),checklist);
    }
}