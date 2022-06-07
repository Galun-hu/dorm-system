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

 Date: 07/06/2022 16:46:58
*/


// ----------------------------
// Collection structure for role
// ----------------------------
db.getCollection("role").drop();
db.createCollection("role");
db.getCollection("role").createIndex({
    "role_id": NumberInt("1")
}, {
    name: "role_id"
});

// ----------------------------
// Documents of role
// ----------------------------
db.getCollection("role").insert([ {
    _id: ObjectId("62909495904bda207565985f"),
    "role_id": 1,
    name: "ROLE_admin",
    nameZh: "系统管理员",
    _class: "com.joy.dorm.system.model.Role"
} ]);
db.getCollection("role").insert([ {
    _id: ObjectId("62909496904bda2075659860"),
    name: "ROLE_dorm",
    nameZh: "宿舍管理员",
    _class: "com.joy.dorm.system.model.Role",
    "role_id": 2
} ]);
