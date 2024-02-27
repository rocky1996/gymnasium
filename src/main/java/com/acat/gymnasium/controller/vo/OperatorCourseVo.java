package com.acat.gymnasium.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorCourseVo {

    private Integer userId;

    private Integer operatorId;

    private Integer keCourse;
}
