package com.acat.gymnasium.controller.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GymnasiumTrainerBo {

    private Integer id;

    private String trainerNickname;

    private String trainerIntroduction;

    private String role;
}
