package com.joy.dorm.outLate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel("晚归表")
@Document("t_outlate")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Outlate {
    public static final String tableName = "t_outlate";

    @ApiModelProperty("晚归_id")
    @Id
    private String _id;

    @ApiModelProperty("晚归id")
    @AutoIncKey
    private Integer id;
    @ApiModelProperty("学号")
    private Integer student_id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("宿舍楼号")
    private Integer building_id;
    @ApiModelProperty("房间号")
    private Integer rome_id;
    @ApiModelProperty("晚归时间")
    private Date outlate_time;

    @ApiModelProperty("宿舍楼名称")
    private String building_name;
    @ApiModelProperty("宿舍楼类型")
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

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getOutlate_time() {
        return outlate_time;
    }

    public void setOutlate_time(Date outlate_time) {
        this.outlate_time = outlate_time;
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
        return "Outlate{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", student_id=" + student_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", building_id=" + building_id +
                ", rome_id=" + rome_id +
                ", outlate_time=" + outlate_time +
                ", building_name='" + building_name + '\'' +
                ", building_type='" + building_type + '\'' +
                '}';
    }
}
