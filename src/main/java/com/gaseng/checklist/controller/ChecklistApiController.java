package com.gaseng.checklist.controller;

import com.gaseng.checklist.dto.ChecklistResponse;
import com.gaseng.checklist.dto.ChecklsitRequest;
import com.gaseng.checklist.service.ChecklistService;
import com.gaseng.global.annotation.ExtractPayload;
import com.gaseng.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ResponseBody
@RequiredArgsConstructor
@Controller
@RequestMapping("/checklist")
public class ChecklistApiController {
    private final ChecklistService checklistService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/create")
    public BaseResponse<Long> create(@ExtractPayload Long memId, @RequestBody @Valid ChecklsitRequest request){
        return new BaseResponse<>(checklistService.join(memId,request.toChecklist()));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/update/{id}")
    public BaseResponse<Long> update(@ExtractPayload Long memId, @RequestBody @Valid ChecklsitRequest request) {
        return new BaseResponse<>(checklistService.join(memId,request.toChecklist()));
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/update/{id}")
    public BaseResponse<Long> updateChecklist(@ExtractPayload Long memId, @RequestBody @Valid ChecklsitRequest request) {
        return new BaseResponse<>(checklistService.updateChecklist(memId, request.toChecklist()));
    }

}
