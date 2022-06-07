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

 Date: 07/06/2022 16:47:43
*/


// ----------------------------
// Collection structure for t_inc
// ----------------------------
db.getCollection("t_inc").drop();
db.createCollection("t_inc");

// ----------------------------
// Documents of t_inc
// ----------------------------
db.getCollection("t_inc").insert([ {
    _id: ObjectId("6290cc3a219c513e6c924a7f"),
    collName: "t_building",
    incKey: "id",
    incId: NumberInt("23")
} ]);
db.getCollection("t_inc").insert([ {
    _id: ObjectId("6291d0b553680000a8004a96"),
    collName: "admin",
    incKey: "id",
    incId: 21
} ]);
db.getCollection("t_inc").insert([ {
    _id: ObjectId("6291d0c153680000a8004a97"),
    collName: "role",
    incKey: "role_id",
    incId: 2
} ]);
db.getCollection("t_inc").insert([ {
    _id: ObjectId("62933480411a30d76a26e247"),
    collName: "visitor",
    incKey: "id",
    incId: NumberInt("144")
} ]);
db.getCollection("t_inc").insert([ {
    _id: ObjectId("62933d8a411a30d76a26e842"),
    collName: "repair",
    incKey: "id",
    incId: NumberInt("151")
} ]);
db.getCollection("t_inc").insert([ {
    _id: ObjectId("6294cc171c10434ca8aa9570"),
    collName: "t_outlate",
    incKey: "id",
    incId: NumberInt("6")
} ]);
db.getCollection("t_inc").insert([ {
    _id: ObjectId("6296145f76a301728790eb2f"),
    collName: "t_health",
    incKey: "id",
    incId: NumberInt("278")
} ]);
db.getCollection("t_inc").insert([ {
    _id: ObjectId("6298c242746ef292c3bac381"),
    collName: "t_building_admin",
    incKey: "id",
    incId: NumberInt("12")
} ]);
