package club.banyuan.test;

import club.banyuan.dao.OrderDao;
import club.banyuan.dao.ProductDao;
import club.banyuan.entity.Order;
import club.banyuan.entity.Product;
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
import java.util.List;

public class TestOrderDao {
    InputStream ins;
    SqlSession session;
    OrderDao orderDao;
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
        orderDao = session.getMapper(OrderDao.class);
    }

    @Test
    public void testAddOrder(){
        Order order = new Order();
        order.setUserId(38);
        order.setLoginName("Rlin001");
        order.setUserAddress("banyuan");
        order.setCreateTime(new Date());
        order.setCost(10000.0);
        order.setSerialNumber("1232342313r212314");
        int i = -1;
        i = orderDao.addOrder(order);
        System.out.println(i);

    }

    @Test
    public void testSelectByUserId(){
        List<Order> orderList = null;
        orderList = orderDao.selectByUserId(38);
        for (Order order : orderList) {
            System.out.println(order);
        }
    }




    @After
    public void destrory() throws IOException {
        session.commit();
        session.close();
        ins.close();
    }
}
