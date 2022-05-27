package com.joy.dorm;

import com.joy.dorm.system.dao.Impl.AdminDaoImpl;
import com.joy.dorm.system.dao.Impl.RoleDaoImpl;
import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.model.Role;
import com.joy.dorm.system.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class DormApplicationTest {

    @Autowired
    AdminService adminService;


    @Autowired
    AdminDaoImpl adminDao;

    @Autowired
    RoleDaoImpl roleDao;

    @Test
    public void test1(){
        Admin admin = new Admin();
        admin.setUsername("李四");
        admin.setPassword("$2a$10$YyGDL6WdQ71sp1xXWV1GI.g4aXWm7Bp2GoPtom4Dea7xAkueob.xu");
        admin.setSex("男");
        admin.setPhone("1234567911");
        admin.setCompany("宿舍管理中心");
        admin.setEnabled(true);
        admin.setRoleId("62909496904bda2075659860");
        admin.setCreateTime(new Date());
        adminService.insert(admin);
    }

    @Test
    public void test2(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

    @Test
    public void test3(){
        Role role = new Role();
        role.setName("ROLE_admin");
        role.setNameZh("系统管理员");
        Role role1 = new Role();
        role1.setName("ROLE_dorm");
        role1.setNameZh("宿舍管理员");
        roleDao.insert(role);
        roleDao.insert(role1);
    }

    @Test
    public void test4(){
        List<Role> allRoles = roleDao.getAllRoles();
        for (Role allRole : allRoles) {
            System.out.println(allRole);
        }
    }

    @Test
    public void test5(){
        Admin admin = adminService.getUsername("张三");
        System.out.println(admin);
    }
}
