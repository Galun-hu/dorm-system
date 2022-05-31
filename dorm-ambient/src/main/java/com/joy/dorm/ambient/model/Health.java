package com.joy.dorm.ambient.model;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@ApiModel(description = "卫生信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("t_health")
public class Health {
    public static final String tableName = "t_health";

    @ApiModelProperty("卫生信息_id")
    @Id
    private String _id;

    @AutoIncKey
    @ApiModelProperty("卫生信息id")
    private Integer id;
    @ApiModelProperty("宿舍楼id")
    private Integer building_id;
    @ApiModelProperty("房间号")
    private Integer rome_id;
    @ApiModelProperty("楼层")
    private Integer floor;
    @ApiModelProperty("卫生等级")
    private String health_level;
    @ApiModelProperty("创建时间")
    private Date create_time;


    @ApiModelProperty("宿舍楼名称")
    private String building_name;

    @ApiModelProperty("宿舍类型")
    private String building_type;

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

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    public Integer getRome_id() {
        return rome_id;
    }

    public void setRome_id(Integer rome_id) {
        this.rome_id = rome_id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getHealth_level() {
        return health_level;
    }

    public void setHealth_level(String health_level) {
        this.health_level = health_level;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getBuilding_type() {
        return building_type;
    }

    public void setBuilding_type(String building_type) {
        this.building_type = building_type;
    }

    @Override
    public String toString() {
        return "Health{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", building_id=" + building_id +
                ", rome_id=" + rome_id +
                ", floor=" + floor +
                ", health_level='" + health_level + '\'' +
                ", create_time=" + create_time +
                ", building_name='" + building_name + '\'' +
                ", building_type='" + building_type + '\'' +
                '}';
    }
}
