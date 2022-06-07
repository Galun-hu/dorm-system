/*
 Navicat Premium Data Transfer

 Source Server         : 106.55.18.86
 Source Server Type    : MongoDB
 Source Server Version : 50006
 Source Host           : 106.55.18.86:27017
 Source Schema         : dormitory

 Target Server Type    : MongoDB
 Target Server Version : 50006
 File Encoding         : 65001

 Date: 07/06/2022 16:46:51
*/


// ----------------------------
// Collection structure for repair
// ----------------------------
db.getCollection("repair").drop();
db.createCollection("repair");
db.getCollection("repair").createIndex({
    buildingId: NumberInt("1")
}, {
    name: "buildingId"
});
db.getCollection("repair").createIndex({
    name: NumberInt("1")
}, {
    name: "name"
});

// ----------------------------
// Documents of repair
// ----------------------------
db.getCollection("repair").insert([ {
    _id: ObjectId("629e261bb6421653ba2f8b92"),
    id: NumberInt("149"),
    dormId: NumberInt("1"),
    buildingId: NumberInt("1"),
    number: "211",
    name: "供应商",
    phone: "13315462541",
    content: "风扇坏了",
    remark: "搞快点",
    enabled: false,
    createTime: ISODate("2022-06-06T16:06:51.173Z"),
    floor: "2",
    _class: "com.joy.dorm.repair.model.Repair"
} ]);
db.getCollection("repair").insert([ {
    _id: ObjectId("629e26afb6421653ba2f8b93"),
    id: NumberInt("150"),
    dormId: NumberInt("1"),
    buildingId: NumberInt("17"),
    number: "211",
    name: "张学友",
    phone: "17608512709",
    content: "风扇坏了",
    remark: "搞快点!!!!!!",
    enabled: false,
    createTime: ISODate("2022-06-06T16:09:19.44Z"),
    floor: "2",
    _class: "com.joy.dorm.repair.model.Repair"
} ]);
db.getCollection("repair").insert([ {
    _id: ObjectId("629eda0a28a10a57aea2b89e"),
    id: NumberInt("151"),
    dormId: NumberInt("1"),
    buildingId: NumberInt("2"),
    number: "411",
    name: "烧鹅",
    phone: "17817065307",
    content: "123465",
    remark: "123456",
    enabled: false,
    createTime: ISODate("2022-06-07T04:54:34.283Z"),
    floor: "4",
    _class: "com.joy.dorm.repair.model.Repair"
} ]);
