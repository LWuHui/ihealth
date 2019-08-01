package com.kiwi.upms.config;

import cn.hutool.crypto.SecureUtil;
import com.kiwi.upms.security.UmpsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jenny
 * @since 2019/6/23 21:27
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户service
     */
    @Autowired
    private UmpsUserDetailsService umpsUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(umpsUserDetailsService).passwordEncoder(new PasswordEncoder() {
            //将数据库查询出来的密码encodedPassword和表单中传过来的密码rawPassword进行比对，注意这里密码必须加密
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String encode = SecureUtil.md5(String.valueOf(rawPassword));
                encodedPassword = encodedPassword.replace("\r\n", "");
                boolean result = encodedPassword.equals(encode);
                return result;
            }

            @Override
            public String encode(CharSequence rawPassword) {
                return SecureUtil.md5(String.valueOf(rawPassword));
            }
        });


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
