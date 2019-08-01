package com.kiwi.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kiwi.upms.comm.RestResponse;
import com.kiwi.upms.comm.enums.UpmsSysMsgEnum;
import com.kiwi.upms.comm.enums.UpmsUserMsgEnum;
import com.kiwi.upms.comm.util.I18nUtil;
import com.kiwi.upms.comm.util.UUIDUtil;
import com.kiwi.upms.dto.UserFormDTO;
import com.kiwi.upms.dto.UserSearchDTO;
import com.kiwi.upms.entity.UpmsUser;
import com.kiwi.upms.mapper.user.UpmsUserMapper;
import com.kiwi.upms.service.UpmsUserService;
import com.kiwi.upms.vo.UserInfoVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务实现
 *
 * @author jenny
 * @since 2019/6/9 13:26
 */
@Service("upmsUserService")
public class UpmsUserServiceImpl implements UpmsUserService, UserDetailsService {


    /**
     * 用户持久层
     */
    @Autowired
    private UpmsUserMapper upmsUserMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


    /**
     * 查询用户分页信息
     *
     * @param page      分页对象
     * @param searchDTO 查询信息
     * @return 用户分页对象
     */
    @Override
    public RestResponse selectUserPage(Page page, UserSearchDTO searchDTO) {
        RestResponse restResponse = new RestResponse();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (searchDTO != null) {
            if (StringUtils.isNotBlank(searchDTO.getUserName())) {
                queryWrapper.eq("username", searchDTO.getUserName());
            }
        }
        IPage<UpmsUser> upmsUserIPage = upmsUserMapper.selectPage(page, queryWrapper);
        Page<UserInfoVO> userInfoVOPage = new Page<>();
        if (CollectionUtils.isNotEmpty(upmsUserIPage.getRecords())) {
            List<UserInfoVO> userInfoVOList = new ArrayList<>(upmsUserIPage.getRecords().size());
            for (UpmsUser upmsUser : upmsUserIPage.getRecords()) {
                UserInfoVO userInfoVO = new UserInfoVO();
                BeanUtils.copyProperties(upmsUser, userInfoVO);
                userInfoVOList.add(userInfoVO);
            }
            userInfoVOPage.setRecords(userInfoVOList);
            userInfoVOPage.setTotal(upmsUserIPage.getTotal());
        }
        restResponse.setCode(UpmsSysMsgEnum.SYS_SUCCESS.getCode());
        restResponse.setMsg(I18nUtil.toLocale(UpmsSysMsgEnum.SYS_SUCCESS.getMsg()));
        restResponse.setData(userInfoVOPage);
        return restResponse;
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public RestResponse getUserById(String userId) {
        RestResponse restResponse = new RestResponse();
        UpmsUser upmsUser = upmsUserMapper.selectById(userId);
        UserInfoVO userInfoVO = new UserInfoVO();
        if (upmsUser != null) {
            BeanUtils.copyProperties(upmsUser, userInfoVO);
        }
        restResponse.setCode(UpmsSysMsgEnum.SYS_SUCCESS.getCode());
        restResponse.setMsg(I18nUtil.toLocale(UpmsSysMsgEnum.SYS_SUCCESS.getMsg()));
        restResponse.setData(userInfoVO);
        return restResponse;
    }

    /**
     * 用户新增或者更新  有用户ID更新，无用户ID新增
     *
     * @param userFormDTO 用户保存对象
     * @return 返回消息
     */
    @Override
    public RestResponse addOrUpdate(UserFormDTO userFormDTO) {
        RestResponse restResponse = new RestResponse();
        UpmsUser upmsUser = new UpmsUser();
        BeanUtils.copyProperties(userFormDTO, upmsUser);
        if (StringUtils.isNotBlank(upmsUser.getId())) {
            restResponse = updateUser(upmsUser);
        } else {
            restResponse = saveUser(upmsUser);
        }
        return restResponse;
    }

    /**
     * 根据用户ID删除用户信息
     *
     * @param userId 用户ID
     * @return 返回消息
     */
    @Override
    public RestResponse deleteUserById(String userId) {
        RestResponse restResponse = new RestResponse();
        upmsUserMapper.deleteById(userId);
        restResponse.setCode(UpmsSysMsgEnum.SYS_SUCCESS.getCode());
        restResponse.setMsg(I18nUtil.toLocale(UpmsSysMsgEnum.SYS_SUCCESS.getMsg()));
        return restResponse;
    }




    /**
     * 更新用户
     *
     * @param upmsUser 用户信息
     * @return 返回消息
     */
    private RestResponse updateUser(UpmsUser upmsUser) {
        RestResponse restResponse = new RestResponse();
        if (StringUtils.isBlank(upmsUser.getId())) {
            restResponse.setCode(UpmsUserMsgEnum.USER_ID_IS_NULL.getCode());
            restResponse.setMsg(I18nUtil.toLocale(UpmsUserMsgEnum.USER_ID_IS_NULL.getMsg()));
            return restResponse;
        }
        upmsUserMapper.updateById(upmsUser);
        restResponse.setCode(UpmsSysMsgEnum.SYS_SUCCESS.getCode());
        restResponse.setMsg(I18nUtil.toLocale(UpmsSysMsgEnum.SYS_SUCCESS.getMsg()));
        return restResponse;
    }


    /**
     * 插入用户
     *
     * @param upmsUser 用户信息
     * @return 返回消息
     */
    private RestResponse saveUser(UpmsUser upmsUser) {
        RestResponse restResponse = new RestResponse();
        upmsUser.setId(UUIDUtil.getUuid());
        //用户名相同，不允许保存
        QueryWrapper ueryWrapper = new QueryWrapper();
        ueryWrapper.eq("username", upmsUser.getUsername());
        UpmsUser user = upmsUserMapper.selectOne(ueryWrapper);
        if (user != null) {
            restResponse.setCode(UpmsUserMsgEnum.USER_ALREADY_EXISTS.getCode());
            restResponse.setMsg(I18nUtil.toLocale(UpmsUserMsgEnum.USER_ALREADY_EXISTS.getMsg()));
            return restResponse;
        }
        restResponse.setCode(UpmsSysMsgEnum.SYS_SUCCESS.getCode());
        restResponse.setMsg(I18nUtil.toLocale(UpmsSysMsgEnum.SYS_SUCCESS.getMsg()));
        return restResponse;
    }


}
