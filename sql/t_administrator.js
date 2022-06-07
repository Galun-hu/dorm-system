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

 Date: 07/06/2022 16:47:14
*/


// ----------------------------
// Collection structure for t_administrator
// ----------------------------
db.getCollection("t_administrator").drop();
db.createCollection("t_administrator");

// ----------------------------
// Documents of t_administrator
// ----------------------------
db.getCollection("t_administrator").insert([ {
    _id: ObjectId("6290dc3a7f1000004b000e65"),
    id: 1,
    name: "王心凌",
    sex: "女",
    age: 18,
    phone: "135665577889",
    created: "2022-5-27",
    modified: "2022-5-27"
} ]);
db.getCollection("t_administrator").insert([ {
    _id: ObjectId("6290df197f1000004b000e67"),
    id: 2,
    name: "罗永浩",
    sex: "男",
    age: 45,
    phone: 123123123213,
    created: "2022-5-22",
    modified: "2022-3-32"
} ]);
