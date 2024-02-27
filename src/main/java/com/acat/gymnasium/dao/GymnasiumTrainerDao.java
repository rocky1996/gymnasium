package com.acat.gymnasium.dao;

import com.acat.gymnasium.domain.GymnasiumTrainer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymnasiumTrainerDao extends JpaRepository<GymnasiumTrainer,Integer> {

    @Query(value = "select e from GymnasiumTrainer e where e.username = ?1 and e.password = ?2", nativeQuery = false)
    GymnasiumTrainer GymnasiumLogin(String userName, String password);

    @Query(value = "select e from GymnasiumTrainer e", nativeQuery = false)
    List<GymnasiumTrainer> getTrainerInfoFromGly(@Param("id") Integer role);

    @Query(value = "select e from GymnasiumTrainer e where e.id = ?1", nativeQuery = false)
    List<GymnasiumTrainer> getTrainerInfoFromJl(@Param("id") Integer id);

    @Query(value = "select e from GymnasiumTrainer e where e.id = ?1", nativeQuery = false)
    GymnasiumTrainer selectById(@Param("id") Integer id);

    List<GymnasiumTrainer> findGymnasiumTrainerByUsernameEquals(String username);
}
