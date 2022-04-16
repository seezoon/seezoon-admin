package com.seezoon.admin.interfaces.sys.web;

import com.seezoon.admin.domain.sys.repository.SysUserRepository;
import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.admin.interfaces.sys.vo.UserInfoVo;
import com.seezoon.admin.interfaces.sys.vo.UserInfoVo.RoleInfoVo;
import com.seezoon.security.SecurityUtils;
import com.seezoon.web.api.Result;
import com.seezoon.web.controller.BaseController;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 适配vben 前端接口
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserInfoController extends BaseController {

    private final SysUserRepository sysUserRepository;

    /**
     * 用户信息接口，可以放入角色，用于控制菜单
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result<UserInfoVo> getUserInfo() {
        Serializable userId = SecurityUtils.getUserId();
        SysUserPO sysUserPO = sysUserRepository.find((Integer) userId);
        final UserInfoVo userInfoVo = UserInfoVo.builder().userId(sysUserPO.getUserId())
                .username(sysUserPO.getUsername()).realName(sysUserPO.getName()).avatar(sysUserPO.getPhoto()).build();
        List<RoleInfoVo> roles = new ArrayList<>();
        userInfoVo.setRoles(roles);
        return Result.ok(userInfoVo);
    }

    /**
     * 获取权限码
     *
     * @return
     */
    @GetMapping("/getPermCode")
    public Result<Set> getPermCode() {
        Serializable userId = SecurityUtils.getUserId();
        Set<String> permissions = new HashSet<>();
        permissions.add("01");
        return Result.ok(permissions);
    }

    /**
     * 获取菜单
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/getMenuList")
    public void getMenuList(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (InputStream resourceAsStream = this.getClass().getClassLoader()
                .getResourceAsStream("menu.json"); OutputStream out = response.getOutputStream()) {
            byte[] bytes = resourceAsStream.readAllBytes();
            out.write(bytes);
        }
    }
}
