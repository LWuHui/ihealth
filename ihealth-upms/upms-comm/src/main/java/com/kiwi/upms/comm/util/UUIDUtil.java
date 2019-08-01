package com.kiwi.upms.comm.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author jenny
 * @since 2019/6/22 21:33
 */
public class UUIDUtil {

    /**
     * 过去随机编码
     * @return 32位UUID
     */
    public static String getUuid() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }
}
