package com.seezoon.admin.interfaces.sys.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * vben 用户信息接口
 */
@Data
@Builder
public class UserInfoVo {

    private Integer userId;
    private String username;
    private String realName;
    private String avatar;
    /**
     * 角色
     */
    private List<RoleInfoVo> roles;

    @Data
    @AllArgsConstructor
    public static class RoleInfoVo {

        private String roleName;
        private String value;
    }
}
