package com.mins.model;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/29
 * No struggle, talent how to match the willfulness.
 * Description: 权限实体类
 */
@Data
public class Permission {

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 角色
     */
    private List<Role> roles;
}
