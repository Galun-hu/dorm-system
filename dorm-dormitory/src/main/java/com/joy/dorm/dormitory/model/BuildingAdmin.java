package com.joy.dorm.dormitory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel(description = "宿舍楼管理员关联实体类")
@Document("t_building_admin")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuildingAdmin {
    public static final String tableName = "t_building_admin";

    @Id
    @ApiModelProperty("主键_id")
    private String _id;

    @AutoIncKey
    @ApiModelProperty("关联表id")
    private Integer id;

    @ApiModelProperty("宿舍楼id")
    private Integer building_id;
    @ApiModelProperty("宿舍管理员id")
    private Integer admin_id;

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

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BuildingAdmin{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", building_id=" + building_id +
                ", admin_id=" + admin_id +
                '}';
    }
}
