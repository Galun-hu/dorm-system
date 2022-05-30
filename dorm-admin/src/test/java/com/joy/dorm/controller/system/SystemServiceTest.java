package com.joy.dorm.controller.system;

import com.joy.dorm.dormitory.dao.impl.AdministratorDaoImpl;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.impl.AdministratorServiceImpl;
import com.joy.dorm.dormitory.service.impl.BuildingServiceImpl;
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

    @Autowired
    BuildingServiceImpl buildingService;

    @Autowired
    AdministratorDaoImpl administratorDao;

//    @Test
//    public void test1(){
////        List<Admin> allAdmin = adminService.getAllAdmin("张",1);
////        for (Admin admin : allAdmin) {
////            System.out.println(admin);
////        }
////        Admin admin = new Admin();
////        admin.setId(1);
////        if (admin.getName()==null){
////            System.out.println("123131");
////        }
//        Integer id = administratorDao.findBuildingIdByDormAdminId(1);
//        System.out.println(id);
//        Building building = buildingService.getBuildingWithId(id);
//        System.out.println(building.get_id()+" "+building.getType());
//    }
}
