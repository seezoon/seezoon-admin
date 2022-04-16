package com.seezoon.admin.application.sys.context;


import com.seezoon.mybatis.repository.spi.UserContext;
import com.seezoon.security.SecurityUtils;
import com.seezoon.security.User;

public class SecurityContext implements UserContext {


    @Override
    public Object getId() {
        final User user = SecurityUtils.getUserInfo();
        return null != user ? user.getUserId() : SecurityUtils.SUPER_ADMIN_USER_ID;
    }

}