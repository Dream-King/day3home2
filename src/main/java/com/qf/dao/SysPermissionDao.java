package com.qf.dao;

import com.qf.pojo.TbSysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 长风 on 2019/11/22.
 */
@Mapper
public interface SysPermissionDao {
    List<TbSysPermission> findUserPermissionByName(String name);
}
