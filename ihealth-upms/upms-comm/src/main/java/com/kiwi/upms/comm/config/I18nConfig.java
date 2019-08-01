package com.kiwi.upms.comm.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 国际化配置
 *
 * @author jenny
 * @since 2019/6/14 22:27
 */
@Configuration
public class I18nConfig {


    /**
     * 用于解析消息的策略接口，支持这些消息的参数化和国际化。
     *
     * @return 消息源
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}
