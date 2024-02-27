package com.acat.gymnasium.service;

import com.acat.gymnasium.domain.GymnasiumOperatorRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GymnasiumOperatorRecordService {

    List<GymnasiumOperatorRecord> getGymnasiumOperatorRecordList(Integer userId);

    List<GymnasiumOperatorRecord> findGymnasiumOperatorRecordsByUserIdList(List<Integer> userIdList);
}
