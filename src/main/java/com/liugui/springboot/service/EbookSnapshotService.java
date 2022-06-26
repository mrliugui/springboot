package com.liugui.springboot.service;

import com.liugui.springboot.vo.EbookSnapshotVo;

public interface EbookSnapshotService {
  int updateEbookSnapshot();

    EbookSnapshotVo getStatistic();
}
