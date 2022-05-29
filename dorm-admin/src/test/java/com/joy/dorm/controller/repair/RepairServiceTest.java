package com.joy.dorm.controller.repair;

import com.joy.dorm.repair.model.Repair;
import com.joy.dorm.repair.service.RepairService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class RepairServiceTest {

    @Autowired
    RepairService repairService;

    @Test
    public void test1(){
        Repair repair = new Repair();
        repair.setBuildingId(1);
        repair.setDormId(2);
        repair.setNumber("411");
        repair.setName("张泽彬");
        repair.setPhone("12345678911");
        repair.setContent("热水管爆了");
        repair.setRemark("已经有一阵子了");
        repair.setEnabled(false);
        repair.setCreateTime(new Date());
        repair.setGoodsTime(null);
        repairService.addRepair(repair);
    }

}
