package com.liugui.springboot.service.serviceImpl;

import com.liugui.springboot.dao.EbookSnapshotMapper;
import com.liugui.springboot.service.EbookSnapshotService;
import com.liugui.springboot.vo.EbookSnapshotVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

;
@Service
public class EbookSnapshotServiceImpl implements EbookSnapshotService {

    @Resource
    private EbookSnapshotMapper ebookSnapshotMapper;

    @Override
    public int updateEbookSnapshot() {
        ebookSnapshotMapper.updateSnapshotMapper();
        return 0;
    }

    @Override
    public EbookSnapshotVo getStatistic() {
        EbookSnapshotVo statistic = ebookSnapshotMapper.getStatistic();
        return statistic;
    }
}
