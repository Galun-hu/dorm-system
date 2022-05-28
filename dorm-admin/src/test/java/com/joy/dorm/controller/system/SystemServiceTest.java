package com.joy.dorm.controller.system;

import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SystemServiceTest {

    @Autowired
    AdminService adminService;

    @Test
    public void test1(){
        List<Admin> allAdmin = adminService.getAllAdmin();
        for (Admin admin : allAdmin) {
            System.out.println(admin);
        }
    }
}
