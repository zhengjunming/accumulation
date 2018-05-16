package com.mins.model;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/29
 * No struggle, talent how to match the willfulness.
 * Description: 用户实体类
 */
@Data
public class User {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private List<Role> roles;
}
