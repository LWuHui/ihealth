package com.kiwi.upms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户保存对象
 *
 * @author jenny
 * @since 2019/6/17 22:29
 */
@Data
public class UserFormDTO {

    private String id;

    @NotBlank(message = "USERNAME_CAN_NOT_NULL")
    private String username;

    @NotBlank(message = "PASSWORD_CAN_NOT_NULL")
    private String password;

}
