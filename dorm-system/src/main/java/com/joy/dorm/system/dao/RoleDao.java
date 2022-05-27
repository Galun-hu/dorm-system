package com.joy.dorm.system.dao;

import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.model.Role;

import java.util.List;

public interface RoleDao {
    //添加角色
    int insert(Role role);

    //修改角色
    int update(Role role);

    //删除角色
    int delete(String id);

    //查询角色
    List<Role> getAllRoles();

    Role getByIdRole(String id);
}
