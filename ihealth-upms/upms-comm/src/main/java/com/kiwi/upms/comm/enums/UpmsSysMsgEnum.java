package com.kiwi.upms.comm.enums;

/**
 * UPMS模块系统消息
 *
 * @author jenny
 * @since 2019/6/9 22:13
 */
public enum UpmsSysMsgEnum {

    /**
     * 成功
     */
    SYS_SUCCESS("200", "SUCCESS"),

    /**
     * 失败
     */
    SYS_FAIL("500", "FAIL"),

    /**
     * 未知
     */
    SYS_UNKNOW("-1", "UNKNOW");

    private String code;

    private String msg;


    UpmsSysMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
