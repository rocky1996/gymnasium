package com.acat.gymnasium.dao;

import com.acat.gymnasium.domain.GymnasiumOperatorRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymnasiumOperatorRecordDao extends JpaRepository<GymnasiumOperatorRecord,Integer> {

    @Query(value = "select e from GymnasiumOperatorRecord e where e.userId = ?1", nativeQuery = false)
    List<GymnasiumOperatorRecord> getGymnasiumOperatorRecordList(@Param("userId") Integer userId);

    List<GymnasiumOperatorRecord> findGymnasiumOperatorRecordsByUserIdIn(List<Integer> userId);
}
