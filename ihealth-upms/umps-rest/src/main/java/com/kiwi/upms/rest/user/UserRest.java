package com.kiwi.upms.rest.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kiwi.upms.comm.RestResponse;
import com.kiwi.upms.dto.UserFormDTO;
import com.kiwi.upms.dto.UserSearchDTO;
import com.kiwi.upms.service.UpmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户rest服务
 *
 * @author jenny
 * @since 2019/6/9 8:59
 */
@RestController
@RequestMapping(value = "user")
public class UserRest {


    /**
     * 用户服务
     */
    @Autowired
    private UpmsUserService upmsUserService;


    /**
     * 查询用户分页列表
     *
     * @param userSearchDTO 用户查询对象
     * @return 用户分页rest
     */
    @RequestMapping(value = "getPage")
    public RestResponse getPage(@RequestBody UserSearchDTO userSearchDTO) {
        Page page = new Page();
        return upmsUserService.selectUserPage(page, userSearchDTO);
    }

    /**
     * 查询用户详情
     *
     * @param userId 用户ID
     * @return 用户详情rest
     */
    @RequestMapping("getById/{userId}")
    public RestResponse getById(@PathVariable String userId) {
        return upmsUserService.getUserById(userId);

    }

    /**
     * 新增或者更新
     * @param userFormDTO 用户表单
     * @return 消息返回
     */
    @RequestMapping("addOrUpdate")
    public RestResponse addOrUpdate(@RequestBody @Valid UserFormDTO userFormDTO) {
        return upmsUserService.addOrUpdate(userFormDTO);

    }

    /**
     * 根据用户ID删除用户
     * @param userId 用户ID
     * @return 删除信息
     */
    @RequestMapping("delById/{userId}")
    public RestResponse delete(@PathVariable String userId) {
        return upmsUserService.deleteUserById(userId);

    }
}
