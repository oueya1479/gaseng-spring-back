package com.gaseng.sharehouse.controller;

import com.gaseng.file.repository.FileRepository;
import com.gaseng.file.service.FileService;
import com.gaseng.global.annotation.ExtractPayload;
import com.gaseng.sharehouse.dto.SharehouseRequest;
import com.gaseng.sharehouse.service.SharehouseService;
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

    @PostMapping(value = "/create")
    public void create(@ExtractPayload Long memId, @RequestParam(value="file") MultipartFile file, @Valid SharehouseRequest request) throws IOException {

        sharehouseService.join(memId,file,request.toSharehouse());
    }

    @PostMapping(value = "/update")
    public void updateChecklist(@ExtractPayload Long memId, @RequestBody @Valid SharehouseRequest request) {
        sharehouseService.updateChecklist(memId, request.toSharehouse());
    }
}
