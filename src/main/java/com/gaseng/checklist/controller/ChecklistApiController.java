package com.gaseng.checklist.controller;

import com.gaseng.checklist.dto.PostCreateChecklistDto;
import com.gaseng.checklist.dto.PostUpdateChecklistDto;
import com.gaseng.checklist.service.ChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@RequiredArgsConstructor
@Controller
@RequestMapping("/checklist")
public class ChecklistApiController {
    private final ChecklistService checklistService;
    @PostMapping(value = "/create")
    public void create(@RequestBody PostCreateChecklistDto request){
        checklistService.join(request.toChecklist());
    }

    @PostMapping(value = "/update")
    public void updateChecklist(@RequestBody PostUpdateChecklistDto request) {
        checklistService.updateChecklist(request.member(),request.toChecklist());
    }

}
