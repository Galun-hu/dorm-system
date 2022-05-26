package com.joy.dorm.system.service;

import com.joy.dorm.system.dao.Impl.AdminDaoImpl;
import com.joy.dorm.system.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

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
        return admin;
    }
}
