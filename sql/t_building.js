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

 Date: 07/06/2022 16:47:23
*/


// ----------------------------
// Collection structure for t_building
// ----------------------------
db.getCollection("t_building").drop();
db.createCollection("t_building");
db.getCollection("t_building").createIndex({
    "administrators.role.role_id": NumberInt("1")
}, {
    name: "administrators.role.role_id"
});
db.getCollection("t_building").createIndex({
    "administrator.role.role_id": NumberInt("1")
}, {
    name: "administrator.role.role_id"
});

// ----------------------------
// Documents of t_building
// ----------------------------
db.getCollection("t_building").insert([ {
    _id: ObjectId("628f9d72a86a000010001357"),
    id: NumberInt("1"),
    name: "男1栋",
    type: "男生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("1970-01-01T00:00:02.022Z"),
    modified: ISODate("2022-06-06T15:45:20.726Z"),
    _class: "com.joy.dorm.dormitory.model.Building",
    administrators: [ ],
    tableName: "t_building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b4b"),
    id: NumberInt("2"),
    name: "男2栋",
    type: "男生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.343Z"),
    modified: ISODate("2022-05-30T08:44:09.343Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b4c"),
    id: NumberInt("3"),
    name: "男3栋",
    type: "男生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.377Z"),
    modified: ISODate("2022-06-06T13:25:52.901Z"),
    _class: "com.joy.dorm.dormitory.model.Building",
    tableName: "t_building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b4d"),
    id: NumberInt("4"),
    name: "男4栋",
    type: "男生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.449Z"),
    modified: ISODate("2022-05-30T08:44:09.449Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b4e"),
    id: NumberInt("5"),
    name: "男5栋",
    type: "男生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.523Z"),
    modified: ISODate("2022-05-30T08:44:09.523Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b4f"),
    id: NumberInt("6"),
    name: "男6栋",
    type: "男生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.596Z"),
    modified: ISODate("2022-05-30T08:44:09.596Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b50"),
    id: NumberInt("7"),
    name: "女1栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.673Z"),
    modified: ISODate("2022-05-30T08:44:09.673Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b51"),
    id: NumberInt("8"),
    name: "女2栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.747Z"),
    modified: ISODate("2022-05-30T08:44:09.747Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b52"),
    id: NumberInt("9"),
    name: "女3栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.821Z"),
    modified: ISODate("2022-05-30T08:44:09.821Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629483d90071000032001b53"),
    id: NumberInt("10"),
    name: "女4栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:09.893Z"),
    modified: ISODate("2022-05-30T08:44:09.893Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484080071000032001b54"),
    id: NumberInt("11"),
    name: "女5栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:56.732Z"),
    modified: ISODate("2022-05-30T08:44:56.732Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484080071000032001b55"),
    id: NumberInt("12"),
    name: "女6栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:56.769Z"),
    modified: ISODate("2022-05-30T08:44:56.769Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484080071000032001b56"),
    id: NumberInt("13"),
    name: "女7栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:56.842Z"),
    modified: ISODate("2022-05-30T08:44:56.842Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484080071000032001b57"),
    id: NumberInt("14"),
    name: "女8栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:56.912Z"),
    modified: ISODate("2022-05-30T08:44:56.912Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484080071000032001b58"),
    id: NumberInt("15"),
    name: "女9栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:56.986Z"),
    modified: ISODate("2022-05-30T08:44:56.986Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484090071000032001b59"),
    id: NumberInt("16"),
    name: "女10栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:57.061Z"),
    modified: ISODate("2022-05-30T08:44:57.061Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484090071000032001b5a"),
    id: NumberInt("17"),
    name: "c17栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:57.137Z"),
    modified: ISODate("2022-05-30T08:44:57.137Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484090071000032001b5b"),
    id: NumberInt("18"),
    name: "c18栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:57.219Z"),
    modified: ISODate("2022-05-30T08:44:57.219Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484090071000032001b5c"),
    id: NumberInt("19"),
    name: "c19栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:57.292Z"),
    modified: ISODate("2022-05-30T08:44:57.292Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
db.getCollection("t_building").insert([ {
    _id: ObjectId("629484090071000032001b5d"),
    id: NumberInt("20"),
    name: "c16栋",
    type: "女生宿舍",
    "person_num": NumberInt("800"),
    created: ISODate("2022-05-30T08:44:57.369Z"),
    modified: ISODate("2022-05-30T08:44:57.369Z"),
    _class: "com.joy.dorm.dormitory.model.Building"
} ]);
