package com.seezoon.admin.domain.sys.repository.mapper;

import com.seezoon.admin.domain.sys.repository.po.SysUserPO;
import com.seezoon.mybatis.repository.mapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户信息
 *
 * @author seezoon-generator 2022年4月10日 下午11:46:22
 */
@Repository
@Mapper
public interface SysUserMapper extends CrudMapper<SysUserPO, Integer> {

}