package com.qf.controller;

import com.qf.pojo.TbSysUser;
import com.qf.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;


/**
 * Created by 长风 on 2019/11/21.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /*IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
    SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
    try {
        subject.login(usernamePasswordToken);
        System.out.println("登录成功");
        return "redirect:/items/show";
    }catch (Exception e){
        System.out.println("登录失败");
        return "login";
    }
*/
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()) {
                return "redirect:/items/show";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public String insertUser(TbSysUser tbSysUser) {

        Date date = new Date(new java.util.Date().getTime());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        simpleDateFormat.format(date);
        tbSysUser.setCreateTime(date);
        tbSysUser.setState(1);
        System.out.println(tbSysUser);
        sysUserService.insertUser(tbSysUser);
        return "redirect:login";
    }
}
