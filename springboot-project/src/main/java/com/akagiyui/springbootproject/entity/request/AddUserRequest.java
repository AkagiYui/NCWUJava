package com.akagiyui.springbootproject.entity.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 添加用户 请求
 * @author AkagiYui
 */
@Data
public class AddUserRequest {
    /**
     * 用户名
     */
    @NotBlank(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be empty")
    private String password;

    /**
     * 昵称
     */
    @Size(max = 20, message = "Nickname length must be less than 20")
    private String nickname;

    /**
     * 邮箱
     */
    @Email(message = "Email format is incorrect")
    @NotBlank(message = "Email cannot be empty")
    private String email;
}
