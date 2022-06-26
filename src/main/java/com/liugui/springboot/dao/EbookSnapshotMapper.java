package com.liugui.springboot.dao;

import com.liugui.springboot.pojo.EbookSnapshot;
import com.liugui.springboot.vo.EbookSnapshotVo;

public interface EbookSnapshotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EbookSnapshot record);

    int insertSelective(EbookSnapshot record);

    EbookSnapshot selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EbookSnapshot record);

    int updateByPrimaryKey(EbookSnapshot record);

    void updateSnapshotMapper();

    EbookSnapshotVo getStatistic();

}