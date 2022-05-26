package com.joy.dorm;

import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
public class DormApplicationTest {

    @Autowired
    AdminService adminService;

    @Test
    public void test1(){
        Admin admin = new Admin();
        admin.setUsername("张三");
        admin.setPassword("$2a$10$fF2aibSDBPJkn.HAk23u8u.nytqKZffU1yjizCSFwCC13ie5waVCq");
        admin.setSex("男");
        admin.setPhone("1234567911");
        admin.setCompany("教务处");
        admin.setEnabled(true);
        admin.setCreateTime(new Date());
        adminService.insert(admin);
    }

    @Test
    public void test2(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

}
