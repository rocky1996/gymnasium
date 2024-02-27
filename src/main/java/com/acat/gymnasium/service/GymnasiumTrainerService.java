package com.acat.gymnasium.service;

import com.acat.gymnasium.constants.RestResult;
import com.acat.gymnasium.controller.vo.RegisterVo;
import com.acat.gymnasium.domain.GymnasiumTrainer;

import java.util.List;

public interface GymnasiumTrainerService {

    /**
     * 用戶登錄
     * @param userName
     * @param password
     * @return
     */
    GymnasiumTrainer GymnasiumLogin(String userName, String password);

    /**
     * 管理员端获取用户
     * @param roleId
     * @return
     */
    List<GymnasiumTrainer> getTrainerInfoFromGly(Integer roleId);

    /**
     * 普通教练获取用户
     * @param id
     * @return
     */
    List<GymnasiumTrainer> getTrainerInfoFromJl(Integer id);

    /**
     *
     * @param username
     * @return
     */
    List<GymnasiumTrainer> findGymnasiumTrainerByUsernameEquals(String username);

    /**
     *
     * @param registerVo
     * @return
     */
    void saveBean(RegisterVo registerVo);

    void deleteById(Integer id);
}
