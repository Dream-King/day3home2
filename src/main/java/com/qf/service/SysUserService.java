package com.qf.service;

import com.qf.pojo.TbSysUser;

/**
 * Created by 长风 on 2019/11/22.
 */
public interface SysUserService {
    TbSysUser findByUserName(String username);
    int insertUser(TbSysUser tbSysUser);
}
