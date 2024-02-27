package com.acat.gymnasium.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCourseVo {

    private String userName;

    private Integer currentCourse;

    private Integer gender;

    private String phone;

    private Integer trainerId;
}
