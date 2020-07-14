package club.banyuan.test;

import club.banyuan.dao.AdminDao;
import club.banyuan.dao.AuctionDao;
import club.banyuan.entity.Admin;
import club.banyuan.entity.Auction;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAdminDao {
    InputStream ins;
    SqlSession session;
    AdminDao adminDao;
    @Before
    public void init() throws IOException {
        // 加载配置文件
        ins = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取用于创建SqlSessionFactory对象的类的对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = builder.build(ins);
        // 创建SqlSession对象
        session = sqlSessionFactory.openSession();
        // 获取接口的实现类对象
        // 动态代理设计模式，获取接口的实现类对象
        adminDao = session.getMapper(AdminDao.class);
    }


    @Test
    public void testLOgin(){

        Map map = new HashMap();
        map.put("userName", "Rlin99");
        map.put("password", "123");
        Admin admin = adminDao.getLoginUser(map);
        System.out.println(admin);
    }

    @After
    public void destrory() throws IOException {
        session.commit();
        session.close();
        ins.close();
    }
}
