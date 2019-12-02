package com.qf.dao;

import com.qf.pojo.TbSysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 长风 on 2019/11/22.
 */
@Mapper
public interface SysUserDao {
    TbSysUser findByUserName(String username);
    int insertUser(TbSysUser tbSysUser);
}
