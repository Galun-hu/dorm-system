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

 Date: 07/06/2022 16:45:37
*/


// ----------------------------
// Collection structure for admin
// ----------------------------
db.getCollection("admin").drop();
db.createCollection("admin");
db.getCollection("admin").createIndex({
    "role.role_id": NumberInt("1")
}, {
    name: "role.role_id"
});
db.getCollection("admin").createIndex({
    id: NumberInt("1")
}, {
    name: "id"
});
db.getCollection("admin").createIndex({
    username: NumberInt("1")
}, {
    name: "username",
    unique: true
});
db.getCollection("admin").createIndex({
    name: NumberInt("1")
}, {
    name: "name"
});
db.getCollection("admin").createIndex({
    roleId: NumberInt("1")
}, {
    name: "roleId"
});

// ----------------------------
// Documents of admin
// ----------------------------
db.getCollection("admin").insert([ {
    _id: "629094bd0a9f754e3ba5a81f",
    _class: "com.joy.dorm.system.model.Admin",
    company: "教务处",
    createTime: ISODate("2022-06-06T00:00:00.027Z"),
    enabled: "true",
    id: NumberInt("1"),
    name: "张三",
    password: "$2a$10$ze6cMq4OZPffDzZH.bntEOby88g35UlHBhGUuZza.lkRfPJHkDVDu",
    phone: "1234567911",
    remark: "",
    roleId: NumberInt("1"),
    sex: "男",
    username: "admin"
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("629788bb9dbf054385be396d"),
    id: NumberInt("2"),
    username: "dorm_admin",
    password: "$2a$10$o0GwF.T.pPQukLctFsazvuTCN5t/mgtXd1PQ7utl8VXC8V3jgqQba",
    name: "李四",
    sex: "男",
    phone: "17817065307",
    company: "宿舍管理中心",
    enabled: true,
    remark: "",
    roleId: NumberInt("1"),
    createTime: ISODate("2022-06-01T15:41:47.643Z"),
    _class: "com.joy.dorm.system.model.Admin"
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("62979014543a845ee323ba60"),
    id: NumberInt("8"),
    username: "Llll",
    password: "$2a$10$3rb4n3sHlIvkoj2ufEB22ufErXvBD3Ri37Z9h/pnc0NqU.iJAdCAW",
    name: "文阿姨",
    sex: "女",
    phone: "13416132015",
    company: "学生处",
    enabled: true,
    remark: "",
    roleId: NumberInt("1"),
    createTime: ISODate("2022-06-01T16:13:08.311Z"),
    _class: "com.joy.dorm.system.model.Admin"
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("6298db8bbeb0373409eced2c"),
    id: NumberInt("11"),
    username: "hh1",
    password: "",
    name: "张阿姨",
    sex: "女",
    phone: "13112487265",
    enabled: true,
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-02T15:47:23.385Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator",
    company: "学生处",
    remark: ""
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("6298db8ebeb0373409eced2e"),
    id: NumberInt("12"),
    username: "hh12",
    password: "$2a$10$acCx.oyqINteervYGhGQ/.1SBPwagfTF1pF719ZpOBFXkeDLnWFfC",
    name: "李阿姨",
    sex: "女",
    phone: "1236675634",
    enabled: true,
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-02T15:47:26.005Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator",
    company: "学生处",
    remark: ""
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("6298db8fbeb0373409eced30"),
    id: NumberInt("13"),
    username: "hh123",
    password: "",
    name: "王阿姨",
    sex: "女",
    phone: "13315462541",
    enabled: true,
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-02T15:47:27.852Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator",
    company: "学生处",
    remark: ""
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("6298db92beb0373409eced32"),
    id: NumberInt("14"),
    username: "hh1234",
    password: "",
    name: "刘阿姨",
    sex: "女",
    phone: "15269653631",
    enabled: true,
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-02T15:47:30.317Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator",
    company: "学生处",
    remark: ""
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("6298dbc3beb0373409eced34"),
    id: NumberInt("15"),
    username: "hh12345671",
    password: "",
    name: "方阿姨",
    sex: "女",
    phone: "1236675634",
    enabled: true,
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-02T15:48:19.825Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator",
    tableName: "admin",
    company: "学生处",
    remark: ""
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("6298dbc6beb0373409eced36"),
    id: NumberInt("16"),
    username: "hh123456",
    password: "$2a$10$wNoiVnu.25siLaL18Y8WWO9OuGb9Ow7WqY5Cz11wdhD02btSxw1vO",
    name: "邓阿姨",
    sex: "女",
    phone: "1236675634",
    enabled: true,
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-02T15:48:22.35Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator",
    company: "学生处",
    remark: ""
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("6298dbc8beb0373409eced38"),
    id: NumberInt("17"),
    username: "hh1234567",
    password: "",
    name: "赵阿姨",
    sex: "女",
    phone: "15265419856",
    enabled: true,
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-02T15:48:24.391Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator",
    company: "学生处",
    remark: ""
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("629e025cbd50fd27e34eddd8"),
    id: NumberInt("18"),
    username: "qwer",
    password: "",
    name: "韩阿姨",
    sex: "女",
    phone: "13416132015",
    company: "学生处",
    enabled: true,
    remark: "",
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-06T13:34:20.878Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator"
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("629e141b3b64136e8e37699a"),
    id: NumberInt("19"),
    username: "adminssss",
    password: "",
    name: "冯阿姨",
    sex: "女",
    phone: "13416132015",
    company: "学生处",
    enabled: true,
    remark: "",
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-06T14:50:03.687Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator"
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("629e21ceb6421653ba2f8b90"),
    id: NumberInt("20"),
    username: "mryang",
    password: "",
    name: "杨阿姨",
    sex: "女",
    phone: "13315462541",
    company: "学生处",
    enabled: true,
    remark: "",
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-06T15:48:30.435Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator"
} ]);
db.getCollection("admin").insert([ {
    _id: ObjectId("629ec534b14b6e7537740c11"),
    id: NumberInt("21"),
    username: "yy",
    password: "$2a$10$o5/qxvSEab99GD3xKYdLP.AYipB8fSGNGhdV81L/dtF85arr1Cp6.",
    name: "蔡徐坤",
    sex: "男",
    company: "学生处",
    enabled: true,
    remark: "asd",
    roleId: NumberInt("2"),
    createTime: ISODate("2022-06-07T03:25:40.657Z"),
    _class: "com.joy.dorm.dormitory.model.Administrator"
} ]);
