package com.acat.gymnasium.enums;

import java.util.HashMap;
import java.util.Map;

public enum GenderEnum {

    MAN(0, "男"),
    WOMAN(1, "女")
    ;

    private Integer code;
    private String genderValue;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    GenderEnum() {}

    GenderEnum(Integer code, String genderValue) {
        this.code = code;
        this.genderValue = genderValue;
    }

    private static Map<Integer, GenderEnum> genderEnumMap = new HashMap<>();
    static {
        for (GenderEnum genderEnum : values()) {
            genderEnumMap.put(genderEnum.getCode(), genderEnum);
        }
    }

    public static GenderEnum getGenderEnumByCode(Integer code) {
        return genderEnumMap.get(code);
    }
}
