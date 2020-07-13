package club.banyuan.test;

import club.banyuan.dao.AddressDao;
import club.banyuan.dao.OrderDao;
import club.banyuan.entity.Address;
import club.banyuan.entity.Order;
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

public class TestAddressDao {
    InputStream ins;
    SqlSession session;
   AddressDao addressDao;
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
        addressDao = session.getMapper(AddressDao.class);
    }

    @Test
    public void testAddAddress(){
        Address address = new Address();
        address.setUserId(38);
        address.setAddress("by@");
        address.setCreateTime(new Date());
        address.setIsDefault(0);
        address.setRemark("@");
        int i = 0;
        i = addressDao.addAddress(address);
        System.out.println(i);
    }

    @Test
    public void testSelectByUserId(){
        List<Address> addressList = null;
        addressList = addressDao.getAddressById(38);
        for (Address address : addressList) {
            System.out.println(address);
        }
    }



    @After
    public void destrory() throws IOException {
        session.commit();
        session.close();
        ins.close();
    }
}
