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

 Date: 07/06/2022 16:47:29
*/


// ----------------------------
// Collection structure for t_building_admin
// ----------------------------
db.getCollection("t_building_admin").drop();
db.createCollection("t_building_admin");

// ----------------------------
// Documents of t_building_admin
// ----------------------------
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("6298db8bbeb0373409eced2d"),
    id: NumberInt("3"),
    "building_id": NumberInt("4"),
    "admin_id": NumberInt("11"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("6298db8ebeb0373409eced2f"),
    id: NumberInt("4"),
    "building_id": NumberInt("1"),
    "admin_id": NumberInt("12"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("6298db8fbeb0373409eced31"),
    id: NumberInt("5"),
    "building_id": NumberInt("5"),
    "admin_id": NumberInt("13"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("6298db92beb0373409eced33"),
    id: NumberInt("6"),
    "building_id": NumberInt("6"),
    "admin_id": NumberInt("14"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("6298dbc3beb0373409eced35"),
    id: NumberInt("7"),
    "building_id": NumberInt("3"),
    "admin_id": NumberInt("15"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("6298dbc6beb0373409eced37"),
    id: NumberInt("8"),
    "building_id": NumberInt("2"),
    "admin_id": NumberInt("16"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("6298dbc8beb0373409eced39"),
    id: NumberInt("9"),
    "building_id": NumberInt("7"),
    "admin_id": NumberInt("17"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("629e025dbd50fd27e34eddd9"),
    "building_id": NumberInt("10"),
    "admin_id": NumberInt("18"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("629e141b3b64136e8e37699b"),
    id: NumberInt("10"),
    "building_id": NumberInt("8"),
    "admin_id": NumberInt("19"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("629e21ceb6421653ba2f8b91"),
    id: NumberInt("11"),
    "building_id": NumberInt("9"),
    "admin_id": NumberInt("20"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
db.getCollection("t_building_admin").insert([ {
    _id: ObjectId("629ec534b14b6e7537740c12"),
    id: NumberInt("12"),
    "building_id": NumberInt("4"),
    "admin_id": NumberInt("21"),
    _class: "com.joy.dorm.dormitory.model.BuildingAdmin"
} ]);
