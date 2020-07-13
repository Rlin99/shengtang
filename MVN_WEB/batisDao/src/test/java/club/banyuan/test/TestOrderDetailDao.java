package club.banyuan.test;

import club.banyuan.dao.OrderDao;
import club.banyuan.dao.OrderDetailDao;
import club.banyuan.entity.Order;
import club.banyuan.entity.OrderDetail;
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

public class TestOrderDetailDao {
    InputStream ins;
    SqlSession session;
    OrderDetailDao orderDetailDao;
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
        orderDetailDao = session.getMapper(OrderDetailDao.class);
    }

    @Test
    public void testAddOrderDetail(){
        int i = 0;
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(44);
        orderDetail.setProductId(744);
        orderDetail.setQuantity(1);
        orderDetail.setCost(10000.0);
        i = orderDetailDao.addOrderDetail(orderDetail);
        System.out.println(i);
    }





    @After
    public void destrory() throws IOException {
        session.commit();
        session.close();
        ins.close();
    }
}
