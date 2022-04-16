package com.seezoon.admin.interfaces.sys.web;

import com.github.pagehelper.PageSerializable;
import com.seezoon.admin.domain.sys.repository.SysUserRepository;
import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.admin.domain.sys.repository.po.SysUserPOCondition;
import com.seezoon.admin.interfaces.sys.ao.UserAo;
import com.seezoon.admin.interfaces.sys.assembler.UserAssembler;
import com.seezoon.admin.interfaces.sys.vo.UserVo;
import com.seezoon.mybatis.repository.constants.Constants;
import com.seezoon.security.PasswordEncoder;
import com.seezoon.security.SecurityUtils;
import com.seezoon.web.api.DefaultCodeMsgBundle;
import com.seezoon.web.api.Result;
import com.seezoon.web.controller.BaseController;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息
 *
 * @author seezoon-generator 2022年4月10日 下午11:46:22
 */
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController extends BaseController {

    private final SysUserRepository sysUserRepository;

    @GetMapping("/query/{userId}")
    public Result<UserVo> query(@PathVariable Integer userId) {
        SysUserPO sysUserPO = sysUserRepository.find(userId);
        return Result.ok(UserAssembler.INSTANCE.toVo(sysUserPO));
    }

    @PostMapping("/query")
    public Result<PageSerializable<UserVo>> query(@Valid @RequestBody SysUserPOCondition condition) {
        PageSerializable<SysUserPO> pageSerializable = sysUserRepository
                .find(condition, condition.getPage(), condition.getPageSize());
        List<UserVo> userVos = pageSerializable.getList().stream().map((v) -> UserAssembler.INSTANCE.toVo(v))
                .collect(Collectors.toList());
        PageSerializable<UserVo> voPageSerializable = new PageSerializable<>(userVos);
        voPageSerializable.setTotal(pageSerializable.getTotal());
        return Result.ok(voPageSerializable);
    }

    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody UserAo ao) {
        if (StringUtils.isNotEmpty(ao.getPassword())) {
            ao.setPassword(PasswordEncoder.encode(ao.getPassword()));
        }
        int count = sysUserRepository.save(UserAssembler.INSTANCE.toPo(ao));
        return count == 1 ? Result.SUCCESS : Result.error(DefaultCodeMsgBundle.SAVE_ERROR, count);
    }

    @PostMapping(value = "/update")
    public Result update(@Valid @RequestBody UserAo ao) {
        if (StringUtils.isNotEmpty(ao.getPassword())) {
            ao.setPassword(PasswordEncoder.encode(ao.getPassword()));
        }
        if (SecurityUtils.isSuperAdmin(ao.getUserId()) && Objects.equals(ao.getStatus(), Constants.INVALID)) {
            return Result.error("超级管理员不能停用");
        }
        int count = sysUserRepository.updateSelective(UserAssembler.INSTANCE.toPo(ao));
        return count == 1 ? Result.SUCCESS : Result.error(DefaultCodeMsgBundle.UPDATE_ERROR, count);
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam Integer userId) {
        if (SecurityUtils.isSuperAdmin(userId)) {
            return Result.error("超级管理员不能删除");
        }
        int count = sysUserRepository.delete(userId);
        return count == 1 ? Result.SUCCESS : Result.error(DefaultCodeMsgBundle.DELETE_ERROR, count);
    }

    @PostMapping(value = "/check_username")
    public Result<Boolean> checkUsername(@RequestParam(required = false) Integer userId,
            @NotBlank @RequestParam String username) {
        SysUserPO sysUserPO = this.sysUserRepository.findByUsername(username);
        return Result.ok(null == sysUserPO || Objects.equals(sysUserPO.getUserId(), userId));
    }

}
