package club.banyuan.test;

import club.banyuan.dao.AuctionDao;
import club.banyuan.dao.ProductDao;
import club.banyuan.entity.Auction;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAuctionDao {
    InputStream ins;
    SqlSession session;
    AuctionDao auctionDao;
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
        auctionDao = session.getMapper(AuctionDao.class);
    }


    @Test
    public void testAdd(){
        Auction auction = new Auction();
        auction.setUserId(1);
        auction.setUesrName("Rlin001");
        auction.setProductId(9);
        auction.setCreateTime(new Date());
        auction.setPrice(9000.0);
        int i = auctionDao.add(auction);
        System.out.println(i);
    }

    @Test
    public void testSelectByProductId(){
        List<Auction> auctionList = auctionDao.selectByProductId(1);
        for (Auction auction : auctionList) {
            System.out.println(auction);
        }
    }

    @Test
    public void testGetProductHighPrice(){
        Auction auction = auctionDao.getProductHighPrice(1);
        System.out.println(auction);
    }

    @After
    public void destrory() throws IOException {
        session.commit();
        session.close();
        ins.close();
    }
}
