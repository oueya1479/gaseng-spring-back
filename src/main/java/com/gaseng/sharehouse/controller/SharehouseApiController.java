package com.gaseng.sharehouse.controller;

import com.gaseng.file.service.FileService;
import com.gaseng.global.annotation.ExtractPayload;
import com.gaseng.global.common.BaseResponse;

import com.gaseng.sharehouse.dto.SharehouseDeleteRequest;
import com.gaseng.sharehouse.dto.SharehouseRequest;
import com.gaseng.sharehouse.dto.SharehouseResponse;
import com.gaseng.sharehouse.dto.SharehouseUpdateRequest;
import com.gaseng.sharehouse.service.SharehouseService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@ResponseBody
@RequiredArgsConstructor
@Controller
@RequestMapping("/sharehouse")
public class SharehouseApiController {
    private final SharehouseService sharehouseService;
    private final FileService fileService;
    
    @Operation(summary = "쉐어하우스 글 조회", description = "사용자가 쉐어하우스 글을 조회합니다.")
    @GetMapping(value = "")
    public BaseResponse<SharehouseResponse> get(
    		@RequestParam(value = "id") Long shrId
    ) {
    	return new BaseResponse<>(sharehouseService.get(shrId));
    }
    
    @Operation(summary = "쉐어하우스 글 조회", description = "사용자가 쉐어하우스 글을 조회합니다.")
    @GetMapping(value = "/all")
    public BaseResponse<List<SharehouseResponse>> getAll() {
    	return new BaseResponse<>(sharehouseService.getAll());
    }

    @Operation(summary = "쉐어하우스 글 생성", description = "사용자가 쉐어하우스 글을 작성합니다.")
    @PostMapping(value = "")
    public BaseResponse<Long> create(
    		@ExtractPayload Long memId,
    		@RequestParam(value = "poster") MultipartFile poster, 
    		@Valid SharehouseRequest request
    ) throws IOException {
    	return new BaseResponse<>(sharehouseService.create(memId, poster, request));
    }
    
//    @Operation(summary = "쉐어하우스 글 수정 입력", description = "사용자가 쉐어하우스 글을 수정입력합니다.")
//    @GetMapping(value = "/{id}")
//    public BaseResponse<SharehouseResponse> getSharehouse(@PathVariable Long id) {
//        return new BaseResponse<>(sharehouseService.get(id));
//    }

    @Operation(summary = "쉐어하우스 글 수정", description = "사용자가 쉐어하우스 글을 수정합니다.")
    @PutMapping(value = "")
    public BaseResponse<Long> update(
    		@ExtractPayload Long memId,
    		@Valid SharehouseUpdateRequest request
    ) {
    	return new BaseResponse<>(sharehouseService.update(memId,request));
    }
    
    @Operation(summary = "쉐어하우스 글 삭제", description = "사용자가 쉐어하우스 글을 삭제합니다.")
    @DeleteMapping(value = "")
    public BaseResponse<Long> delete(
            @ExtractPayload Long memId,
            @Valid SharehouseDeleteRequest request
    ) {
        return new BaseResponse<>(sharehouseService.deleteSharehouse(memId, request.id()));
    }
}
