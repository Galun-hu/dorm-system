package com.joy.dorm.dormitory.model;

import com.joy.dorm.common.utils.AutoIncKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("t_building")
public class Building {
    private static final String tableName = "t_building";

    @Id
    private String _id;

    @AutoIncKey
    private Integer id;
    private String type;
    private Integer person_num;
    private String created;
    private String modified;



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPerson_num() {
        return person_num;
    }

    public void setPerson_num(Integer person_num) {
        this.person_num = person_num;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Building{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", person_num=" + person_num +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                '}';
    }
}
