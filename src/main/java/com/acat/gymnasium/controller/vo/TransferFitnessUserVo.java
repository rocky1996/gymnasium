package com.acat.gymnasium.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferFitnessUserVo {

    private Integer beforeTrainerId;
    private Integer afterTrainerId;
}
