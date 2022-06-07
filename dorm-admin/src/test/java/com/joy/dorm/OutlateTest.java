package com.joy.dorm;

import com.joy.dorm.ambient.dao.IHealthDao;
import com.joy.dorm.ambient.model.Health;
import com.joy.dorm.ambient.service.IHealthService;
import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IAdministratorService;
import com.joy.dorm.dormitory.service.IBuildingService;
import com.joy.dorm.outLate.dao.IOutlateDao;
import com.joy.dorm.utils.DormitoryTool;
import org.junit.jupiter.api.Test;
import org.mockito.internal.creation.SuspendMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

@SpringBootTest
public class OutlateTest {

    @Autowired
    private IOutlateDao outlateDao;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IAdministratorService administratorService;

//    @Test
//    public void test1(){
//        System.out.println(outlateDao.findAllOutlate());
//    }

    @Test
    public void get2(){
        System.out.println(buildingService.getBuildings(null,null,1,10));
    }


//    @Test
//    public void insert(){
//        int in = 1;
//        int o = 0;
//        for (int i = 0;i<5;i++) {
//            List<Administrator> administrators = administratorService.getDormAdmins(null, null,i,10);
//            for (int j = 0;j < administrators.size();j++){
//                if (o < 3){
//                    administratorService.insertDormAdminToBuilding(in,administrators.get(j).getId());
//                    o++;
//                }else {
//                    o = 0;
//                    administratorService.insertDormAdminToBuilding(++in,administrators.get(j).getId());
//                }
//            }
//        }
//    }


    @Test
    public void get(){
        List<Administrator> administrators = administratorService.getDormAdmins(null, null,0,10);
        System.out.println(administrators);
    }

    @Autowired
    private IHealthService healthServicem;
    @Test
    public void insert2(){
        String []l = new String[]{"优","良","差"};
        Random random = new Random();
        for (int i =0;i<5;i++) {

            for (int j = 1;j <= 6;j++){
                int bai = 100 * j;
                for (int z=1;z <= 9;z++ ) {
                    Health health = new Health();
                    health.setBuilding_id(i + 1);
                    Building building = buildingService.getBuildings(null, i + 1, 0, 5).get(0);
                    health.setBuilding_name(building.getName());
                    health.setBuilding_type(building.getType());
                    health.setFloor(j);
                    health.setRome_id(++bai);
                    int ran = random.nextInt(3);
                    health.setHealth_level(l[ran]);
                    healthServicem.addHealth(health);
                }
            }
        }
    }


    @Autowired
    private IBuildingDao buildingDao;

    @Test
    public void geta(){
        List<Administrator> administrators = administratorService.getDormAdminsWithBuildingId(null,null,0,5);
        System.out.println(administrators);
    }
    @Autowired
    private DormitoryTool dormitoryTool;

    @Test
    public void ddd(){
        System.out.println(dormitoryTool.getBuildWithAdminId(3));
    }

    @Autowired
    private IHealthDao healthDao;

    @Test
    public void asd(){
        Long health = healthDao.acountHealths("男生宿舍","2",null);
        System.out.println(health);
    }


    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void mongoTest(){
        LookupOperation lookup = LookupOperation.newLookup()
                .from("t_building_admin")
                .localField("id")
                .foreignField("building_id")
                .as("t_b_a");
        ProjectionOperation project = Aggregation.project("_id","id","name","type","person_num",
                "created","modified").and("t_b_a.admin_id").as("admin_id");
//        Criteria criteria = new Criteria();

        Aggregation aggregation = Aggregation.newAggregation(lookup,project,
                Aggregation.group("id"),
                Aggregation.bucket("amdin_id").andOutput("admin_id").push().as("admin_id"),
                Aggregation.unwind("admin_id"));
//        );
        List<Map> buildings = mongoTemplate.aggregate(aggregation,"t_building",Map.class).getMappedResults();
        System.out.println(buildings);
    }


    @Test
    public void t(){
        Administrator administrator = new Administrator();
//        administrator.setBuilding_id(3);
        administrator.setId(11);
        administrator.setSex("男");
        System.out.println(administratorService.updateDormAdminToBuilding(administrator));
    }

    @Test
    public void insertAdmin(){
        Administrator administrator = new Administrator();
        administrator.setBuilding_id(1);

    }

    @Test
    public void asdasd(){
        System.out.println(administratorService.getDormAdminsWithBuildingId(null,null,0,5).size());
    }

    @Autowired
    private IAdministretorDao administretorDao;

    @Test
    public void aaaa(){
        System.out.println(administretorDao.findAdministratorsWithBuildingId("h",null,1,4));
    }

    @Test
    public void adsd(){
        Administrator administrator = new Administrator();
        administrator.setId(15);
        administrator.setPassword("");
        System.out.println(administratorService.updateDormAdminToBuilding(administrator));
    }

    @Test
    public void asdeee(){
        System.out.println(administretorDao.findAdministratorsWithBuildingId(null,null,0,10).size());
    }

    @Test
    public void kkk(){
        System.out.println(buildingService.getBuildings(null,null,0,10));
    }

    @Test
    public void insasd(){
        Administrator administrator = new Administrator();
        administrator.setRemark("asd");
        administrator.setBuilding_id(4);
        administrator.setSex("男");
        administrator.setCompany("学生处");
        administrator.setName("蔡徐坤");
        administrator.setPassword("123456");
        administrator.setUsername("yy");
        administratorService.insertDormAdminToBuilding(administrator);
    }
}
