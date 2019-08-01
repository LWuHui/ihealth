package com.kiwi.upms.comm;

import lombok.Data;

/**
 * @author jenny
 * @since 2019/6/9 22:09
 */
@Data
public class RestResponse {

    private String code;

    private String msg;

    private Object data;


}
