package com.kiwi.upms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kiwi.upms.comm.RestResponse;
import com.kiwi.upms.dto.UserFormDTO;
import com.kiwi.upms.dto.UserSearchDTO;
import com.kiwi.upms.vo.UserInfoVO;

/**
 * 用户服务接口
 *
 * @author jenny
 * @since 2019/6/9 13:20
 */
public interface UpmsUserService {


    /**
     * 查询用户分页信息
     *
     * @param page      分页对象
     * @param searchDTO 查询信息
     * @return 用户分页对象
     */
    RestResponse selectUserPage(Page page, UserSearchDTO searchDTO);


    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    RestResponse getUserById(String userId);


    /**
     * 用户新增或者更新  有用户ID更新，无用户ID新增
     *
     * @param userFormDTO 用户保存对象
     * @return 返回消息
     */
    RestResponse addOrUpdate(UserFormDTO userFormDTO);

    /**
     * 根据用户ID删除用户信息
     *
     * @param userId 用户ID
     * @return 返回消息
     */
    RestResponse deleteUserById(String userId);



}
