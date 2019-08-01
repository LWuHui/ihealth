package com.kiwi.upms.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 后台用户表
 *
 * @author wuhui
 * @since 2019-06-05
 */
@Data
public class UpmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 公司id(关联公司表)
     */
    private String comId;

    /**
     * -1 不可用 0可用
     */
    private String enable;


}
