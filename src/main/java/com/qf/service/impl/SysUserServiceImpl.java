package com.qf.service.impl;

import com.qf.dao.SysUserDao;
import com.qf.pojo.TbSysUser;
import com.qf.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 长风 on 2019/11/22.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public TbSysUser findByUserName(String username) {
        return sysUserDao.findByUserName(username);
    }

    @Override
    public int insertUser(TbSysUser tbSysUser) {
        return sysUserDao.insertUser(tbSysUser);
    }
}
