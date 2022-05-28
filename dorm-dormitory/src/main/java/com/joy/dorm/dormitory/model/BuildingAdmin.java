package com.joy.dorm.dormitory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("t_building_admin")
public class BuildingAdmin {

    @Id
    private String _id;

    private Integer building_id;
    private String admin_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "BuildingAdmin{" +
                "_id='" + _id + '\'' +
                ", building_id='" + building_id + '\'' +
                ", admin_id='" + admin_id + '\'' +
                '}';
    }
}
