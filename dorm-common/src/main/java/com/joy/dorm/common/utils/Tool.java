package com.joy.dorm.common.utils;

import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;

public class Tool {

    /**
     * 用于更新实体类数据
     * 通过反射遍历传进来的类的字段，然后更新这些字段到Update.class
     * @param domainType
     * @return
     */
    public Update updateFields(Object domainType) {
        Update update = new Update();
        Field[] fields = domainType.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if ("serialVersionUID".equals(field.getName())
                        || "_id".equals(field.getName())
                        || "id".equals(field.getName())) {
                    continue;
                }
                if (field.get(domainType) != null) {
                    update.set(field.getName(), field.get(domainType));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //        /** 基类BaseDO里面的modified字段，手动设置更新！！！ */
//        update.set("modified", this.getNowTime());
        return update;
    }
}
