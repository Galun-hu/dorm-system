package com.joy.dorm.system.dao;

import com.joy.dorm.system.model.Admin;

import java.util.List;

public interface AdminDao {

    //添加管理员
    int insert(Admin admin);

    //修改管理员
    int update(Admin admin);

    //删除管理员
    int delete(String id);

    //查询所有管理员
    List<Admin> getAllAdmin();

    //根据id查管理员
    Admin getByIdAdmin(String id);

    //根据用户名查询管理员
    Admin loadUserByUsername(String username);
}
