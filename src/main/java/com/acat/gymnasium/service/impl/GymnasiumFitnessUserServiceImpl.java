package com.acat.gymnasium.service.impl;

import com.acat.gymnasium.constants.RestResult;
import com.acat.gymnasium.controller.vo.ShoppingCourseVo;
import com.acat.gymnasium.dao.GymnasiumFitnessUserDao;
import com.acat.gymnasium.dao.GymnasiumOperatorRecordDao;
import com.acat.gymnasium.domain.GymnasiumFitnessUser;
import com.acat.gymnasium.domain.GymnasiumOperatorRecord;
import com.acat.gymnasium.enums.OperatorEnum;
import com.acat.gymnasium.enums.RestEnum;
import com.acat.gymnasium.service.GymnasiumFitnessUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class GymnasiumFitnessUserServiceImpl implements GymnasiumFitnessUserService {

    @Resource
    private GymnasiumFitnessUserDao gymnasiumFitnessUserDao;
    @Resource
    private GymnasiumOperatorRecordDao gymnasiumOperatorRecordDao;

    @Override
    @Transactional
    public void shoppingCourse(ShoppingCourseVo shoppingCourseVo) {
        Date date = new Date();
        GymnasiumFitnessUser gymnasiumFitnessUser = new GymnasiumFitnessUser();
        gymnasiumFitnessUser.setCurrentCourse(shoppingCourseVo.getCurrentCourse());
        gymnasiumFitnessUser.setUsername(shoppingCourseVo.getUserName());
        gymnasiumFitnessUser.setGender(shoppingCourseVo.getGender());
        gymnasiumFitnessUser.setPhone(shoppingCourseVo.getPhone().trim());
        gymnasiumFitnessUser.setTrainerId(shoppingCourseVo.getTrainerId());
        gymnasiumFitnessUser.setCreateTime(date);
        gymnasiumFitnessUser.setUpdateTime(date);
        gymnasiumFitnessUserDao.save(gymnasiumFitnessUser);

        GymnasiumOperatorRecord gymnasiumOperatorRecord = new GymnasiumOperatorRecord();
        gymnasiumOperatorRecord.setUserId(gymnasiumFitnessUser.getId());
        gymnasiumOperatorRecord.setOperator(OperatorEnum.MAI_KE.getCode());
        gymnasiumOperatorRecord.setCreateTime(date);
        gymnasiumOperatorRecord.setUpdateTime(date);
        gymnasiumOperatorRecordDao.save(gymnasiumOperatorRecord);
    }

    @Override
    @Transactional
    public RestResult operatorCourse(Integer userId, OperatorEnum operatorEnum, Integer keCourse) {
        GymnasiumFitnessUser gymnasiumFitnessUser = getGymnasiumFitnessUsersByUserId(userId);
        if (gymnasiumFitnessUser == null) {
            return new RestResult<>(RestEnum.USER_IS_EMPTY.getCode(), RestEnum.USER_IS_EMPTY.getMsg(), null);
        }

        Date date = new Date();
        GymnasiumOperatorRecord gymnasiumOperatorRecord = new GymnasiumOperatorRecord();
        gymnasiumOperatorRecord.setUserId(gymnasiumFitnessUser.getId());
        gymnasiumOperatorRecord.setCreateTime(date);
        gymnasiumOperatorRecord.setUpdateTime(date);

        int currentCourse = gymnasiumFitnessUser.getCurrentCourse();
        switch (operatorEnum) {
            case KOU_KE:
                if (currentCourse < 0) {
                    return new RestResult<>(RestEnum.COURSE_NOT_HAVING.getCode(), RestEnum.COURSE_NOT_HAVING.getMsg(), null);
                }

                currentCourse = currentCourse - 1;
                gymnasiumFitnessUser.setCurrentCourse(currentCourse);
                gymnasiumFitnessUserDao.save(gymnasiumFitnessUser);

                gymnasiumOperatorRecord.setOperator(OperatorEnum.KOU_KE.getCode());
                gymnasiumOperatorRecordDao.save(gymnasiumOperatorRecord);
                break;
            case XU_KE:
                currentCourse = currentCourse + keCourse;
                gymnasiumFitnessUser.setCurrentCourse(currentCourse);
                gymnasiumFitnessUserDao.save(gymnasiumFitnessUser);

                gymnasiumOperatorRecord.setOperator(OperatorEnum.XU_KE.getCode());
                gymnasiumOperatorRecordDao.save(gymnasiumOperatorRecord);
                break;
            default:
                break;
        }
        return new RestResult<>(RestEnum.SUCCESS, null);
    }

    @Override
    public GymnasiumFitnessUser getGymnasiumFitnessUsersByUsername(String username) {
        return gymnasiumFitnessUserDao.getGymnasiumFitnessUsersByUsername(username);
    }

    @Override
    public GymnasiumFitnessUser getGymnasiumFitnessUsersByUserId(Integer userId) {
        return gymnasiumFitnessUserDao.getGymnasiumFitnessUsersByUserId(userId);
    }

    @Override
    public List<GymnasiumFitnessUser> getGymnasiumFitnessUsersListByTrainerId(Integer trainerId) {
        return gymnasiumFitnessUserDao.getGymnasiumFitnessUsersListByTrainerId(trainerId);
    }

    @Override
    public List<GymnasiumFitnessUser> findGymnasiumFitnessUsersByTrainerIdIn(List<Integer> trainerIdList) {
        List<GymnasiumFitnessUser> gymnasiumFitnessUserList = gymnasiumFitnessUserDao.findGymnasiumFitnessUsersByTrainerIdIn(trainerIdList);
        if (CollectionUtils.isEmpty(gymnasiumFitnessUserList)) {
            return null;
        }
        return gymnasiumFitnessUserList;
    }

    @Override
    public void transferFitnessUser(Integer afterTrainerId, List<GymnasiumFitnessUser> gymnasiumFitnessUserList) {
        gymnasiumFitnessUserList.stream().forEach(user -> user.setTrainerId(afterTrainerId));
        gymnasiumFitnessUserDao.saveAll(gymnasiumFitnessUserList);
    }
}
