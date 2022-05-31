package com.joy.dorm.ambient.service.impl;

import com.joy.dorm.ambient.dao.IHealthDao;
import com.joy.dorm.ambient.model.Health;
import com.joy.dorm.ambient.service.IHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class HealthServiceImpl implements IHealthService {

    @Autowired
    private IHealthDao healthDao;

    @Override
    public List<Health> getHealths(Integer building_id, String keywords,String building_type, Integer pageNum, Integer pageSize){
        return healthDao.findHealths(building_id,keywords,building_type,pageNum,pageSize);
    }

    @Override
    public Long getHealthsCount(String keywords){
        return healthDao.acountHealths(keywords);
    }

    @Override
    public Integer addHealth(Health health){
        health.setCreate_time(new Date());
        health.set_id(null);
        health.setId(null);
        return healthDao.insertHealth(health);
    }

    @Override
    public long updateHealth(Health health){
        if (health.getId() == null){
            return -1;
        }else {
            health.set_id(null);
            return healthDao.updateHealth(health);
        }
    }

    @Override
    public long deleteHealth(Integer id){
        return healthDao.deleteHealth(id);
    }
}
