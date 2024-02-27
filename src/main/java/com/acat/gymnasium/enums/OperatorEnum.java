package com.acat.gymnasium.enums;

import java.util.HashMap;
import java.util.Map;

public enum OperatorEnum {

    MAI_KE(0, "买课"),
    XU_KE(1, "续课"),
    KOU_KE(2, "扣课"),
    ;

    private Integer code;
    private String value;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperatorEnum() {}

    OperatorEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private static Map<Integer, OperatorEnum> OperatorEnumMap = new HashMap<>();
    static {
        for(OperatorEnum operatorEnum : values()) {
            OperatorEnumMap.put(operatorEnum.getCode(), operatorEnum);
        }
    }

    public static OperatorEnum getOperatorEnumMap(Integer code) {
        return OperatorEnumMap.get(code);
    }
}
