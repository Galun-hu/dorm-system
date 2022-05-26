package com.joy.dorm.dormitory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("t_building")
public class Building {
    @Id
    private String _id;

    private Integer id;
    private String type;
    private Integer administrator;
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

    public Integer getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Integer administrator) {
        this.administrator = administrator;
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
                ", administrator=" + administrator +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                '}';
    }
}
