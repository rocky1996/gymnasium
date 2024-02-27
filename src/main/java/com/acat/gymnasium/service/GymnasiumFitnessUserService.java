package com.acat.gymnasium.service;

import com.acat.gymnasium.constants.RestResult;
import com.acat.gymnasium.controller.vo.ShoppingCourseVo;
import com.acat.gymnasium.domain.GymnasiumFitnessUser;
import com.acat.gymnasium.enums.OperatorEnum;
import org.omg.CORBA.INTERNAL;

import java.util.List;

public interface GymnasiumFitnessUserService {

    /**
     * 买课
     * @param shoppingCourseVo
     */
    void shoppingCourse(ShoppingCourseVo shoppingCourseVo);

    /**
     * 扣课/续课
     * @param userId
     * @param operatorEnum
     * @param keCourse
     * @return
     */
    RestResult operatorCourse(Integer userId, OperatorEnum operatorEnum, Integer keCourse);

    GymnasiumFitnessUser getGymnasiumFitnessUsersByUsername(String username);

    GymnasiumFitnessUser getGymnasiumFitnessUsersByUserId(Integer userId);

    List<GymnasiumFitnessUser> getGymnasiumFitnessUsersListByTrainerId(Integer trainerId);

    List<GymnasiumFitnessUser> findGymnasiumFitnessUsersByTrainerIdIn(List<Integer> trainerIdList);

    void transferFitnessUser(Integer afterTrainerId, List<GymnasiumFitnessUser> gymnasiumFitnessUserList);
}
