package com.joy.dorm.outLate.service.impl;

import com.joy.dorm.outLate.dao.IOutlateDao;
import com.joy.dorm.outLate.model.Outlate;
import com.joy.dorm.outLate.service.IOutlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OutlateServiceImpl implements IOutlateService {

    @Autowired
    private IOutlateDao outlateDao;

    @Override
    public List<Outlate> getAllOutlate(Integer building_id,String keywords,String building_type,Integer pageNum,Integer pageSize){
        return outlateDao.findOutlate(building_id,keywords,building_type,pageNum,pageSize);
    }

    @Override
    public Long getOutlateCount(Integer building_id,String keywords,String building_type){
        return outlateDao.acountOutlate(building_id,keywords,building_type);
    }

//    @Override
//    public List<Outlate> getAllOutlateWithBuildingId(Integer building_id){
//        return outlateDao.findAllOutlateByBuildingId(building_id);
//    }
//
//    @Override
//    public List<Outlate> getAllOutlateWithBuildingIdAndBuildingType(Integer building_id,String building_type){
//        return outlateDao.findAllOutlateByBuildingIdAdndBuildingType(building_id,building_type);
//    }



    @Override
    public Integer addOutlate(Outlate outlate){
        outlate.set_id(null);
        outlate.setId(null);
        outlate.setBuilding_name(null);
        outlate.setBuilding_type(null);
        outlate.setOutlate_time(new Date());
        if (outlate.getStudent_id() == null || outlate.getBuilding_id() == null || outlate.getRome_id() == null){
            return -1;
        }
        return outlateDao.insertOutlate(outlate);
    }

    @Override
    public long updateOutlate(Outlate outlate){
        if (outlate.getId() == null){
            return -1;
        }
        return outlateDao.updateOutlate(outlate);
    }

    @Override
    public long deleteOutlateWithId(Integer id){
        return outlateDao.deleteOutlateById(id);
    }
}
