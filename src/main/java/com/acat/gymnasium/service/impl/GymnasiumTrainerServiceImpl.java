package com.acat.gymnasium.service.impl;

import com.acat.gymnasium.constants.RestResult;
import com.acat.gymnasium.controller.vo.RegisterVo;
import com.acat.gymnasium.dao.GymnasiumTrainerDao;
import com.acat.gymnasium.domain.GymnasiumTrainer;
import com.acat.gymnasium.service.GymnasiumTrainerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class GymnasiumTrainerServiceImpl implements GymnasiumTrainerService {

    @Resource
    private GymnasiumTrainerDao gymnasiumTrainerDao;

    @Override
    public GymnasiumTrainer GymnasiumLogin(String userName, String password) {
        GymnasiumTrainer gymnasiumTrainer = gymnasiumTrainerDao.GymnasiumLogin(userName, password);
        return gymnasiumTrainer;
    }

    @Override
    public List<GymnasiumTrainer> getTrainerInfoFromGly(Integer roleId) {
        return gymnasiumTrainerDao.getTrainerInfoFromGly(roleId);
    }

    @Override
    public List<GymnasiumTrainer> getTrainerInfoFromJl(Integer id) {
        return gymnasiumTrainerDao.getTrainerInfoFromJl(id);
    }

    @Override
    public List<GymnasiumTrainer> findGymnasiumTrainerByUsernameEquals(String username) {
        return gymnasiumTrainerDao.findGymnasiumTrainerByUsernameEquals(username);
    }

    @Override
    public void saveBean(RegisterVo registerVo) {
        Date date = new Date();
        GymnasiumTrainer gymnasiumTrainer = new GymnasiumTrainer();
        gymnasiumTrainer.setUsername(registerVo.getUserName());
        gymnasiumTrainer.setPassword(registerVo.getPassword());
        gymnasiumTrainer.setRole(registerVo.getRole());
        gymnasiumTrainer.setTrainerNickname(registerVo.getTrainerNickname());
        gymnasiumTrainer.setTrainerIntroduction(registerVo.getTrainerIntroduction());
        gymnasiumTrainer.setCreateTime(date);
        gymnasiumTrainer.setUpdateTime(date);
        gymnasiumTrainerDao.save(gymnasiumTrainer);
    }

    @Override
    public void deleteById(Integer id) {
        gymnasiumTrainerDao.deleteById(id);
    }
}
