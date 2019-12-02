package com.qf.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by 长风 on 2019/11/22.
 */
@Data
public class TbSysUser {
    private Integer userid;
    private String loginName;
    private String password;
    private Integer state;
    private Date createTime;
    private String realname;
}
