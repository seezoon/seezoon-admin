package com.seezoon.admin.domain.sys.service;

import com.seezoon.admin.domain.sys.dto.ChangePasswordDto;
import com.seezoon.admin.domain.sys.repository.SysUserRepository;
import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.core.concept.domain.DomainService;
import com.seezoon.core.service.AbstractTransactionService;
import com.seezoon.security.PasswordEncoder;
import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 修改密码
 */
@Service
@RequiredArgsConstructor
public class ChangePasswordService extends AbstractTransactionService implements DomainService {

    private final SysUserRepository sysUserRepository;

    /**
     * 修改密码
     *
     * @param dto
     * @return false 原密码错误
     */
    public boolean change(@Valid ChangePasswordDto dto) {
        SysUserPO sysUser = Objects.requireNonNull(this.sysUserRepository.find(dto.getUserId()),"user must not null");
        boolean matches = PasswordEncoder.matches(dto.getOldPassword(), sysUser.getPassword());
        if (matches) {
            sysUser.setPassword(PasswordEncoder.encode(dto.getNewPassword()));
            this.sysUserRepository.update(sysUser);
            return true;
        }
        return false;
    }
}
