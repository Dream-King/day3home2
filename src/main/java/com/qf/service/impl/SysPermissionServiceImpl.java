package com.qf.service.impl;

import com.qf.dao.SysPermissionDao;
import com.qf.pojo.TbSysPermission;
import com.qf.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 长风 on 2019/11/22.
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    SysPermissionDao sysPermissionDao;
    @Override
    public List<TbSysPermission> findUserPermissionByUserName(String name) {
        return sysPermissionDao.findUserPermissionByName(name);
    }
}
