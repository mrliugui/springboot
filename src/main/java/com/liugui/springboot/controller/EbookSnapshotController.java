package com.liugui.springboot.controller;

import com.liugui.springboot.common.ApiResponse;
import com.liugui.springboot.service.EbookSnapshotService;
import com.liugui.springboot.vo.EbookSnapshotEChartVo;
import com.liugui.springboot.vo.EbookSnapshotVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ebook-snapShot")
public class EbookSnapshotController {

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public ApiResponse getStatistic(){
        List<EbookSnapshotVo> statistic = ebookSnapshotService.getStatistic();
        return ApiResponse.success(statistic);
    }
    @GetMapping("/get-30statistic")
    public ApiResponse get30Statistic(){
        List<EbookSnapshotEChartVo> statistic = ebookSnapshotService.get30Statistic();
        return ApiResponse.success(statistic);
    }
}
