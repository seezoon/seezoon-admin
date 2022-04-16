package com.seezoon.admin.interfaces.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserVo {

    private Integer userId;
    /**
     * 登录名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 头像
     */
    private String photo;

    /**
     * 邮件
     */
    private String email;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}


