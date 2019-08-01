package com.kiwi.upms.comm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author jenny
 * @since 2019/6/14 22:23
 */
@Component
public class I18nUtil {


    private static ResourceBundleMessageSource messageSource;

    /**
     * 在Spring里，静态变量/类变量不是对象的属性，而是一个类的属性，不能用@Autowired一个静态变量（对象），使之成为一个SpringBean。<br />
     * 只能通过setter方法注入，并把类注解成为组件
     *
     * @param source 消息源
     */
    @Autowired
    public void init(ResourceBundleMessageSource source) {
        I18nUtil.messageSource = source;
    }

    /**
     * 抛出校验错误异常
     *
     * @param msgKey 消息key-wordss
     */
    public static String toLocale(String msgKey) {
        // 消息的参数化和国际化配置
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgKey, new Object[0], locale);
    }

}
