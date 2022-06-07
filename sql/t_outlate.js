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

 Date: 07/06/2022 16:47:49
*/


// ----------------------------
// Collection structure for t_outlate
// ----------------------------
db.getCollection("t_outlate").drop();
db.createCollection("t_outlate");

// ----------------------------
// Documents of t_outlate
// ----------------------------
db.getCollection("t_outlate").insert([ {
    _id: ObjectId("62964072f57d6d26f8cf4cf1"),
    id: NumberInt("1"),
    "student_id": NumberInt("1908010722"),
    name: "张学友",
    phone: "13112487265",
    "building_id": NumberInt("8"),
    "rome_id": NumberInt("33333"),
    "outlate_time": ISODate("2022-05-31T16:21:06.163Z"),
    _class: "com.joy.dorm.outLate.model.Outlate",
    tableName: "t_outlate",
    floor: NumberInt("2")
} ]);
db.getCollection("t_outlate").insert([ {
    _id: ObjectId("629640c6f57d6d26f8cf4cf2"),
    id: NumberInt("2"),
    "student_id": NumberInt("1908010722"),
    name: "张泽彬",
    phone: "15632512851",
    "building_id": NumberInt("8"),
    "rome_id": NumberInt("33333"),
    "outlate_time": ISODate("2022-05-31T16:22:30.293Z"),
    _class: "com.joy.dorm.outLate.model.Outlate",
    floor: NumberInt("1"),
    tableName: "t_outlate"
} ]);
db.getCollection("t_outlate").insert([ {
    _id: ObjectId("629841158586f2341d5b0fb7"),
    id: NumberInt("3"),
    "student_id": NumberInt("1809078631"),
    name: "何华柱",
    phone: "13564522765",
    "building_id": NumberInt("1"),
    "rome_id": NumberInt("602"),
    "outlate_time": ISODate("2022-06-02T04:48:21.975Z"),
    floor: NumberInt("4"),
    _class: "com.joy.dorm.outLate.model.Outlate",
    tableName: "t_outlate"
} ]);
db.getCollection("t_outlate").insert([ {
    _id: ObjectId("629df9e98ad3ed6c9cc34358"),
    id: NumberInt("6"),
    "student_id": NumberInt("1908010722"),
    name: "好厉害",
    phone: "13112487265",
    "building_id": NumberInt("8"),
    "rome_id": NumberInt("33333"),
    "outlate_time": ISODate("2022-06-06T12:58:17.471Z"),
    floor: NumberInt("2"),
    _class: "com.joy.dorm.outLate.model.Outlate"
} ]);
