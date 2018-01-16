package io.github.biezhi.mybatis.dao;

import io.github.biezhi.mybatis.mapper.VillageMapper;
import io.github.biezhi.mybatis.model.Village;
import io.github.biezhi.mybatis.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class VillageDAO {

    public void schema(){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        VillageMapper mapper = session.getMapper(VillageMapper.class);
        mapper.schema();

        session.commit();
        session.close();
    }

    public void save(Village village) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("io.github.biezhi.mybatis.mapper.VillageMapper.insertVillage", village);
        session.commit();
        session.close();
    }

    public void update(Village village) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("io.github.biezhi.mybatis.mapper.VillageMapper.updateVillage", village);
        session.commit();
        session.close();
    }

    public void delete(Integer id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("io.github.biezhi.mybatis.mapper.VillageMapper.deleteVillage", id);
        session.commit();
        session.close();
    }

    public Village getData(Integer id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Village    village = session.selectOne("io.github.biezhi.mybatis.mapper.VillageMapper.selectVillage", id);
        session.close();
        return village;
    }
    
} 