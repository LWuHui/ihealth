package com.kiwi.upms.busine.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kiwi.upms.bo.user.UserInfoBO;
import com.kiwi.upms.busine.UpmsUserBusiness;
import com.kiwi.upms.entity.UpmsUser;
import com.kiwi.upms.mapper.user.UpmsUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jenny
 * @since 2019/7/30 22:22
 */
@Service("userBusiness")
public class UpmsUserBusinessImpl implements UpmsUserBusiness {

    /**
     * 用户持久层
     */
    @Autowired
    private UpmsUserMapper upmsUserMapper;


    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public UserInfoBO getUserByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isEmpty(userName)) {
            return null;
        } else {
            queryWrapper.eq("username", userName);
        }
        UserInfoBO userInfoBO = new UserInfoBO();
        UpmsUser upmsUser = upmsUserMapper.selectOne(queryWrapper);
        if (upmsUser != null) {
            BeanUtils.copyProperties(upmsUser, userInfoBO);
        }

        return userInfoBO;
    }
}
