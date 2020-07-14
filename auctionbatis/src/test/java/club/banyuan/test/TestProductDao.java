package club.banyuan.test;

import club.banyuan.dao.ProductDao;
import club.banyuan.dao.UserDao;
import club.banyuan.entity.Product;
import club.banyuan.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestProductDao {
    InputStream ins;
    SqlSession session;
    ProductDao productDao;
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
        productDao = session.getMapper(ProductDao.class);
    }

    @Test
    public void testGetProductById (){

        Product product = productDao.getProductById(3);
        System.out.println(product);
    }
    @Test
    public void testGetProductBySell(){
        List<Product> productList = productDao.getProductBySell(0);
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Test
    public void testUpdateProduct(){
        Product product = new Product();
        product.setIsSelled(0);
        product.setId(9);
        product.setName("大路灯");
        product.setDescribtion("sdfsdf");
        product.setStartTime(new Date());
        product.setFileName("sdfsf");
        product.setFinishTime(new Date());
        product.setStartPrice(3200.0);
        product.setBasePrice(4999.9);
        int i = productDao.updateProduct(product);
    }
    @Test
    public void testAdd(){
        Product product = new Product();
        product.setIsSelled(0);
        product.setName("路灯");
        product.setDescribtion("sdfsdf");
        product.setStartTime(new Date());
        product.setFileName("sdfsf");
        product.setFinishTime(new Date());
        product.setStartPrice(3200.0);
        product.setBasePrice(4999.9);
        int i = productDao.addProduct(product);
    }

    @Test
    public void testDelete(){
        int i = productDao.deleteProduct(10);
        System.out.println(i);
    }

    @Test
    public void testSelectByOpinion(){
        Map map = new HashMap();
        map.put("describtion", "%所%");
        //map.put("finishTime","2016-06-02 14:51:46");
        List<Product> productList = productDao.getProductByOpinion(map);
        for (Product product : productList) {
            System.out.println(product);
        }
    }




    @After
    public void destrory() throws IOException {
        session.commit();
        session.close();
        ins.close();
    }
}
