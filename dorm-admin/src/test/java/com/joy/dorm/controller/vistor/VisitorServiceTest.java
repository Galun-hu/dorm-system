package com.joy.dorm.controller.vistor;

import com.joy.dorm.visitor.model.Visitor;
import com.joy.dorm.visitor.service.VisitorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class VisitorServiceTest {

    @Autowired
    VisitorService visitorService;

    @Test
    public void test1(){
        Visitor visitor = new Visitor();
//        visitor.setName("张三");
        visitor.setSex("男");
        visitor.setPhone("1234567911");
        visitor.setBuildingId(1);
        visitor.setDormId(2);
        visitor.setRemark("找同学玩");
//        visitor.setCreateTime(new Date());
//        List<Visitor> list = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            visitor.setName("张三"+i);
//            visitor.setCreateTime(new Date());
//        }
    }

    @Test
    public void test2(){

    }
}
