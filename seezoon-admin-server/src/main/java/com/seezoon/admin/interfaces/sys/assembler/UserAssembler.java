package com.seezoon.admin.interfaces.sys.assembler;

import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.admin.interfaces.sys.ao.UserAo;
import com.seezoon.admin.interfaces.sys.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 */
@Mapper
public interface UserAssembler {
    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);
    
    SysUserPO toPo(UserAo ao);
    UserVo toVo(SysUserPO po);
}
