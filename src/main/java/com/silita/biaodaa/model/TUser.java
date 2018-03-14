package com.silita.biaodaa.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TUser {
    private Integer pkid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 0-禁用  1-正常
     */
    private Boolean status;

    /**
     * 1-系统管理员   2-应用管理员  3-操作管理员
     */
    private Boolean permission;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 显示名
     */
    private String displayName;

    /**
     * 人员ID
     */
    private String perId;

    private Date created;

    private Date updated;

}