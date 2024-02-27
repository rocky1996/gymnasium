package com.acat.gymnasium.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {

    private String userName;
    private String password;
    private String rePassword;
    private String trainerNickname;
    private String trainerIntroduction;
    private Integer role;
}
