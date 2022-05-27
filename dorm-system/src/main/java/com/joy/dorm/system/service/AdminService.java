package com.joy.dorm.system.service;

import com.joy.dorm.system.dao.Impl.AdminDaoImpl;
import com.joy.dorm.system.dao.Impl.RoleDaoImpl;
import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    RoleDaoImpl roleDao;

    @Autowired
    AdminDaoImpl adminDao;

    public int insert(Admin admin){
        return adminDao.insert(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminDao.loadUserByUsername(username);
        if (admin==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        Role role = roleDao.getByIdRole(admin.getRoleId());
        admin.setRole(role);
        System.out.println(admin);
        return admin;
    }


    public Admin getUsername(String username) {
        Admin admin = adminDao.loadUserByUsername(username);
        Role role = roleDao.getByIdRole(admin.getRoleId());
        admin.setRole(role);
        return admin;
    }
}
