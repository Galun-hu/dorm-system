package com.joy.dorm.common.utils;

import com.joy.dorm.common.model.incId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 监听器，监听注解了插入数据时需要自动自增的字段
 *
 * 监听器⽤于监听Mongo Event，该类继承AbstractMongoEventListener类
 * 因为我们需要在JAVA对象转换成数据库对象的时候操作id字段实现id⾃增，所以覆盖onBeforeConvert⽅法
 */
@Component
public class SaveEventListener extends AbstractMongoEventListener<Object> {
    private static final Logger logger = LoggerFactory.getLogger(SaveEventListener.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event){
        logger.info(event.getSource().toString());
        Object source = event.getSource();
        if (source != null) {
            ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    // 如果字段添加了我们⾃定义的AutoIncKey注解
                    if (field.isAnnotationPresent(AutoIncKey.class)) {
                        /**
                         * 获取该字段对应的表名称
                         */
                        try {
                            Field tableField = source.getClass().getDeclaredField("tableName");
                            tableField.setAccessible(true);
                            Object tableName = tableField.get(source);
                            if (tableName != null && tableName != ""){
                                /**
                                 * 设置⾃增ID
                                 * */
                                field.set(source, getNextId((String) tableName,field.getName()));
                            }
                        }catch (NoSuchFieldException e){
                            try {
                                throw new NoSuchFieldException("没有这个字段");
                            } catch (NoSuchFieldException ex) {}
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
    }
    private Integer getNextId(String collName,String incKey) {
        Query query = new Query(Criteria.where("collName").is(collName).and("incKey").is(incKey));
        Update update = new Update();
        update.inc("incId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        incId inc= mongoTemplate.findAndModify(query, update, options, incId.class);
        return inc.getIncId();
    }
}
