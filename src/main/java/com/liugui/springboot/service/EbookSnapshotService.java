package com.liugui.springboot.service;

import com.liugui.springboot.vo.EbookSnapshotEChartVo;
import com.liugui.springboot.vo.EbookSnapshotVo;

import java.util.List;

public interface EbookSnapshotService {
  int updateEbookSnapshot();

    List<EbookSnapshotVo> getStatistic();

    List<EbookSnapshotEChartVo> get30Statistic();
}
