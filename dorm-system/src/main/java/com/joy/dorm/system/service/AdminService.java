package com.joy.dorm.system.service;

import com.joy.dorm.system.dao.Impl.AdminDaoImpl;
import com.joy.dorm.system.dao.Impl.RoleDaoImpl;
import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    RoleDaoImpl roleDao;

    @Autowired
    AdminDaoImpl adminDao;


    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    //获取所有管理员和对应的角色
    public List<Admin> getAllAdmin(String keywords,Integer id,int pageNumNew,int pageSize) {
        //排除自己
        return adminDao.getAllAdmin(keywords,id,pageNumNew,pageSize);
    }

    //添加管理员
    public int insert(Admin admin){
        //在这里添加都是系统管理员
        admin.setRoleId(1);
        admin.setEnabled(true);
        admin.setPassword(encoder.encode(admin.getPassword()));
        admin.setCreateTime(new Date());
        return adminDao.insert(admin);
    }

    //修改管理员
    public int update(Admin admin){
        return adminDao.update(admin);
    }

    //修改密码
    public boolean updatePassWord(String oldPassword,String newPassword,Integer id){
        Admin admin = adminDao.getByIdAdmin(id);
        //密码比对
        if (encoder.matches(oldPassword,admin.getPassword())){
            String encode = encoder.encode(newPassword);
            Admin admin1 = new Admin();
            admin1.setId(id);
            admin1.setPassword(encode);
            return adminDao.update(admin1) == 1;
        }
        return false;
    }

    //删除用户
    public int deleteAdmin(Integer id){
        return adminDao.delete(id);
    }


    //获取用户名
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


    public Long getAdminCount(String keywords, Integer id) {
        return adminDao.getAdminCount(keywords,id);
    }
}
