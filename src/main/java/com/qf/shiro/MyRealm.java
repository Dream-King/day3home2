package com.qf.shiro;

import com.qf.pojo.TbSysPermission;
import com.qf.pojo.TbSysUser;
import com.qf.service.SysPermissionService;
import com.qf.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 长风 on 2019/11/21.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;
    //权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录用户名
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //登录名称查询权限
        List<TbSysPermission> userPermissionByUserName = sysPermissionService.findUserPermissionByUserName(primaryPrincipal);
        if(userPermissionByUserName!=null&&userPermissionByUserName.size()>0){
            Collection list=new HashSet<>();
            for (TbSysPermission per:userPermissionByUserName
                 ) {
                list.add(per.getPerName());

            }
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermissions(list);
            return simpleAuthorizationInfo;
        }
        return null;

    }

    //登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取到当前登录的用户名
        String username=(String)token.getPrincipal();
        //通过用户名去数据库查询正确的密码
        TbSysUser user = sysUserService.findByUserName(username);
        if(user!=null){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(),user.getPassword(),getName());
            return simpleAuthenticationInfo;
        }
       return null;
    }
}
