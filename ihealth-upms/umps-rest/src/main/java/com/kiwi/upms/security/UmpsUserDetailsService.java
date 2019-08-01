package com.kiwi.upms.security;

import com.kiwi.upms.bo.user.UserInfoBO;
import com.kiwi.upms.busine.UpmsUserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenny
 * @since 2019/7/30 22:59
 */
public class UmpsUserDetailsService implements UserDetailsService {


    @Autowired
    private UpmsUserBusiness upmsUserBusiness;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户查询用户信息
        UserInfoBO userInfoBO = upmsUserBusiness.getUserByUserName(username);

        UpmsUserDetails upmsUserDetails = new UpmsUserDetails();

        upmsUserDetails.setUserName(userInfoBO.getUsername());
        // 根据用户查询用户对应权限
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        // 设置用户权限
        upmsUserDetails.setAuthorities(authorities);
        return upmsUserDetails;
    }

}
