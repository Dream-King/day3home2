package com.qf.service;

import com.qf.pojo.TbSysPermission;

import java.util.List;

/**
 * Created by 长风 on 2019/11/22.
 */
public interface SysPermissionService {
    public List<TbSysPermission> findUserPermissionByUserName(String name);
}
