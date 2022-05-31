package com.joy.dorm.repair.model;

import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel(description = "维修")
@Document(collection = "repair")
public class Repair {

    public static final String tableName = "repair";

    @Id
    @ApiModelProperty("mongoId")
    private String _id;
    @ApiModelProperty("维修id")
    @AutoIncKey
    private Integer id;
    @ApiModelProperty("接待的舍管id")
    private Integer dormId;
    @ApiModelProperty("宿舍楼Id")
    private Integer buildingId;
    @ApiModelProperty("宿舍号")
    private String number;
    @ApiModelProperty("申报人姓名")
    private String name;
    @ApiModelProperty("申报人手机号")
    private String phone;
    @ApiModelProperty("维修内容")
    private String content;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("是否完成 有两种状态 维修中 已修好 类型布尔")
    private Boolean enabled;
    @ApiModelProperty("申报时间")
    private Date createTime;
    @ApiModelProperty("修好时间")
    private Date goodsTime;
    @ApiModelProperty("宿舍名")
    private String BuiName;
    @ApiModelProperty("宿舍类别")
    private String type;

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

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getGoodsTime() {
        return goodsTime;
    }

    public void setGoodsTime(Date goodsTime) {
        this.goodsTime = goodsTime;
    }


    public String getBuiName() {
        return BuiName;
    }

    public void setBuiName(String buiName) {
        BuiName = buiName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", dormId=" + dormId +
                ", buildingId=" + buildingId +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", enabled=" + enabled +
                ", createTime=" + createTime +
                ", goodsTime=" + goodsTime +
                ", BuiName='" + BuiName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
