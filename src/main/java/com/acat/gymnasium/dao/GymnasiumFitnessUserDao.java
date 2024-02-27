package com.acat.gymnasium.dao;

import com.acat.gymnasium.domain.GymnasiumFitnessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymnasiumFitnessUserDao extends JpaRepository<GymnasiumFitnessUser,Integer> {

    @Query(value = "select e from GymnasiumFitnessUser e where e.trainerId = ?1")
    List<GymnasiumFitnessUser> getGymnasiumFitnessUsersListByTrainerId(Integer trainerId);

    @Query(value = "select e from GymnasiumFitnessUser e where e.username = ?1")
    GymnasiumFitnessUser getGymnasiumFitnessUsersByUsername(String username);

    @Query(value = "select e from GymnasiumFitnessUser e where e.id = ?1")
    GymnasiumFitnessUser getGymnasiumFitnessUsersByUserId(Integer userId);

    List<GymnasiumFitnessUser> findGymnasiumFitnessUsersByTrainerIdIn(List<Integer> trainerIdList);
}
