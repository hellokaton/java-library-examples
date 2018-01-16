package io.github.biezhi.mybatis;

import io.github.biezhi.mybatis.dao.VillageDAO;
import io.github.biezhi.mybatis.model.Village;

public class RunMybatis {

    public static void main(String[] args) {

        VillageDAO villageDAO = new VillageDAO();
        villageDAO.schema();

        //insert
        Village village = new Village();
        village.setName("怒撒之城");
        village.setDistrict("斑马路");
        villageDAO.save(village);
        System.out.println("---保存数据---");

        //update
        village = new Village();
        village.setId(1);
        village.setName("新的巴尔");
        village.setDistrict("张江路");
        villageDAO.update(village);
        System.out.println("---更新数据---");

        //select
        village = villageDAO.getData(1);
        System.out.println("id:" + village.getId() + ", Name:" + village.getName() + ", District:" + village.getDistrict());

        //delete
        villageDAO.delete(1);
        System.out.println("---删除数据---");
    }
}