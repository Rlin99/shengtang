package club.banyuan.test;

import club.banyuan.dao.UserDao;
import club.banyuan.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestUserDao {
    InputStream ins;
    SqlSession session;
    UserDao userDao;
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
        userDao = session.getMapper(UserDao.class);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setUserName("www");
        user.setPassword("123");
        user.setIdCard("123121231231");
        user.setAddress("NanJing");
        user.setPhone("12345678987");
        user.setPostCode("321002");

        int i = userDao.add(user);
        System.out.println(i);
    }

    @Test
    public void testGetLoginUser(){
        Map map = new HashMap();
        map.put("userName", "Rlin001");
        map.put("password", "123");

        User user = userDao.getLoginUser(map);
        System.out.println(user);
    }

    @Test
    public void testSelectUserName(){
        User user = userDao.selectLoginName("Rlin001");
        System.out.println(user);
    }





    @After
    public void destrory() throws IOException {
        session.commit();
        session.close();
        ins.close();
    }
}
