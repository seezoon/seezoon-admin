package com.seezoon.admin.interfaces.sys.assembler;


import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.admin.interfaces.sys.ao.UserAo;
import org.junit.jupiter.api.Test;

class UserAssemblerTest {

    @Test
    void toPo() {
        UserAo userAo = new UserAo();
        userAo.setUsername("user");
        final SysUserPO sysUserPO = UserAssembler.INSTANCE.toPo(userAo);
        System.out.println(sysUserPO.getUsername());
    }
}