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
public class GymnasiumOperatorRecordBo {

    private Integer id;

    private String operator;

    private String userName;

    private String createTime;

    private String updateTime;
}
