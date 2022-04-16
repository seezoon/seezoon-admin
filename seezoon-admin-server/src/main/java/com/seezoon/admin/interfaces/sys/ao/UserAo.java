package com.seezoon.admin.interfaces.sys.ao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAo {

    private Integer userId;
    /**
     * 登录名
     */
    @NotBlank
    @Size(max = 50)
    private String username;

    /**
     * 密码
     */
    @Size(max = 100)
    private String password;

    /**
     * 姓名
     */
    @NotBlank
    @Size(max = 50)
    private String name;

    /**
     * 手机
     */
    @Size(max = 20)
    private String mobile;

    /**
     * 头像
     */
    @Size(max = 100)
    private String photo;

    /**
     * 邮件
     */
    @Size(max = 50)
    private String email;

    @NotNull
    private Integer status;
}
