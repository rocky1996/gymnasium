package com.acat.gymnasium.controller.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GymnasiumFitnessUserBo {

    private Integer id;

    private String username;

    private String gender;

    private String phone;

    private Integer trainerId;

    private String trainerName;

    private Integer currentCourse;

    private String createTime;

    private String updateTime;
}
