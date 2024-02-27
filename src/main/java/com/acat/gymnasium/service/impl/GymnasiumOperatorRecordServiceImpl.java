package com.acat.gymnasium.service.impl;

import com.acat.gymnasium.dao.GymnasiumFitnessUserDao;
import com.acat.gymnasium.dao.GymnasiumOperatorRecordDao;
import com.acat.gymnasium.domain.GymnasiumOperatorRecord;
import com.acat.gymnasium.enums.OperatorEnum;
import com.acat.gymnasium.service.GymnasiumOperatorRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GymnasiumOperatorRecordServiceImpl implements GymnasiumOperatorRecordService {

    @Resource
    private GymnasiumOperatorRecordDao gymnasiumOperatorRecordDao;

    @Override
    public List<GymnasiumOperatorRecord> getGymnasiumOperatorRecordList(Integer userId) {
        List<GymnasiumOperatorRecord> gymnasiumOperatorRecordList = gymnasiumOperatorRecordDao.getGymnasiumOperatorRecordList(userId);
        if (CollectionUtils.isEmpty(gymnasiumOperatorRecordList)) {
            return null;
        }

        return gymnasiumOperatorRecordList.
                stream()
                .sorted(Comparator.comparing(GymnasiumOperatorRecord::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<GymnasiumOperatorRecord> findGymnasiumOperatorRecordsByUserIdList(List<Integer> userIdList) {
        List<GymnasiumOperatorRecord> gymnasiumOperatorRecordList = gymnasiumOperatorRecordDao.findGymnasiumOperatorRecordsByUserIdIn(userIdList);
        if (CollectionUtils.isEmpty(gymnasiumOperatorRecordList)) {
            return null;
        }
        return gymnasiumOperatorRecordList;
    }
}
