package com.acat.gymnasium.enums;

public enum RestEnum {

    SUCCESS(0, "成功"),
    USER_EMPTY(301, "用户不存在"),
    PASSWORD_IS_DISGRESS(302, "密码不一致,请重新注册！！！"),
    USERNAME_IS_REPEAT(303, "用户名已存在,请重新注册!!!"),
    BATCH_QUERY_FIELD_LIST_EMPTY(304, "上传文件数据为空,请重新上传"),
    FIELD_NOT_SUPPORT_DIM_SEARCH(305, ""),
    BATCH_QUERY_FIELD_SIZE_TOO_LARGE(306, "上传文件数量不能超过500条,请重新上传"),
    BATCH_QUERY_FIELD_HAS_SP_CHAR(307, "服务端失败,请排查上传字段是否包含'/'等特殊字符"),
    USERNAME_EMPTY_PARAM(401, "会员姓名不能为空"),
    PASSWORD_EMPTY_PARAM(402, "密码不能为空"),
    USER_IS_EXISTS(403, "该用户已存在,请不要买课,请续课"),
    PHONE_IS_ERROR(405, "手机号格式不对,请重新输入!!!"),
    FEN_YE_ERROR(406, "分页参数错误"),
    MEDIA_SOURCE_ERROR(407, "媒介来源错误"),
    PLEASE_ADD_PARAM(408, "您好,请输入搜索参数,否则结果为空！！！"),
    PARAM_IS_NOT_EMPTY(409, "数据来源或UUID不能为空！！！"),
    HUI_YUAN_OPERATOR_IS_EMPTY(410, "该教练下会员无操作记录！！！"),
    HUI_YUAN_IS_EMPTY(411, "该教练下无会员！！！"),
    XUKE_COURSE_DAYU_0(412, "续课课程必须大于0节！！！"),
    COURSE_NOT_HAVING(413, "当前课程不够扣除！！！"),
    USER_IS_EMPTY(414, "用户不存在"),
//    SYSTEM_ERROR(402, "系统错误"),
//    NO_HAVING_DATA(403, "没有搜索到任何相关数据"),
    FAILED(500, "服务端失败"),
    PLEASE_TRY(504, "没有此等操作,请重试！！！"),
    ;

    private Integer code;
    private String msg;

    RestEnum() {
    }

    RestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
