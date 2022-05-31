package com.joy.dorm;

import com.joy.dorm.outLate.dao.IOutlateDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OutlateTest {

    @Autowired
    private IOutlateDao outlateDao;

    @Test
    public void test1(){
        System.out.println(outlateDao.findAllOutlate());
    }
}
