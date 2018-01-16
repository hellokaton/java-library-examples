package io.github.biezhi.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class App {

    public static SqlSessionFactory factory = null;

    public static void init() {
        String resource = "mybatis-config.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(TransactionTokenMapper.class);
    }
}