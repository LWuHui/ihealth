package com.kiwi.upms.comm.enums;

/**
 * @author jenny
 * @since 2019/6/21 23:28
 */
public enum UpmsUserMsgEnum {

    /**
     * 用户ID为空
     */
    USER_ID_IS_NULL("100001", "USER_ID_IS_NULL"),

    /**
     * 用户已经存在
     */
    USER_ALREADY_EXISTS("100002", "USER_ALREADY_EXISTS"),

    /**
     * 用户名为空
     */
    USER_NAME_IS_NULL("100003","USER_NAME_IS_NULL")


    ;

    private String code;

    private String msg;


    UpmsUserMsgEnum(String code, String msg) {
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
