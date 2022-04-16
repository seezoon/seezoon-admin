package com.seezoon.admin.domain.sys.repository;

import com.seezoon.admin.domain.sys.repository.mapper.SysUserMapper;
import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.admin.domain.sys.repository.po.SysUserPOCondition;
import com.seezoon.mybatis.repository.AbstractCrudRepository;
import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户信息
 *
 * @author seezoon-generator 2022年4月10日 下午11:46:22
 */
@Repository
public class SysUserRepository extends AbstractCrudRepository<SysUserMapper, SysUserPO, Integer> {

    @Transactional(readOnly = true)
    public SysUserPO findByUsername(@NotBlank String username) {
        SysUserPOCondition sysUserPOCondition = new SysUserPOCondition();
        sysUserPOCondition.setUsername(username);
        return this.findOne(sysUserPOCondition);
    }
}
