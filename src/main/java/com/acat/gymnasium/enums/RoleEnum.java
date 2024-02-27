package com.acat.gymnasium.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {

    GUAN_LI_YUAN(0, "管理员"),
    JIAO_LIAN(1, "教练")
    ;

    private Integer code;
    private String role;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    RoleEnum() {}

    RoleEnum(Integer code, String role) {
        this.code = code;
        this.role = role;
    }

    private static Map<Integer, RoleEnum> roleEnumMap = new HashMap<>();
    static {
        for (RoleEnum roleEnum : values()) {
            roleEnumMap.put(roleEnum.getCode(), roleEnum);
        }
    }

    public static RoleEnum getRoleEnumByCode(Integer code) {
        return roleEnumMap.get(code);
    }
}
