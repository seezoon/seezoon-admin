package com.seezoon.admin.application.sys.service;

import com.seezoon.admin.domain.sys.repository.SysUserRepository;
import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.core.concept.application.ApplicationService;
import com.seezoon.security.AbstractUserDetailsServiceImpl;
import com.seezoon.security.SeezoonUserDetails;
import com.seezoon.security.User;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 处理用户登录逻辑
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService extends AbstractUserDetailsServiceImpl implements ApplicationService {

    private final SysUserRepository sysUserRepository;

    @Override
    public SeezoonUserDetails getSeezoonUserDetails(HttpServletRequest request, String loginType, String username,
            String password) {
        SysUserPO sysUserPO = sysUserRepository.findByUsername(username);
        if (null == sysUserPO) {
            throw new UsernameNotFoundException(username + " not found");
        }
        User user = new User();
        user.setUserId(sysUserPO.getId());
        SeezoonUserDetails<User> seezoonUserDetails = new SeezoonUserDetails<>(user, username, sysUserPO.getPassword());
        return seezoonUserDetails;
    }
}
