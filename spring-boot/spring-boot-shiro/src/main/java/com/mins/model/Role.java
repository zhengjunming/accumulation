package com.mins.model;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/29
 * No struggle, talent how to match the willfulness.
 * Description: 角色实体类
 */
@Data
public class Role {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色
     */
    private List<User> users;

    /**
     * 权限
     */
    private List<Permission> permissions;
}
