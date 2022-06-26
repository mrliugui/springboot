package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.service.EbookSnapshotService;
import com.liugui.springboot.vo.EbookSnapshotVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/ebook-snapShot")
public class EbookSnapshot {

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public ApiResponse getStatistic(){
        EbookSnapshotVo statistic = ebookSnapshotService.getStatistic();
        return ApiResponse.success(statistic);
    }
}