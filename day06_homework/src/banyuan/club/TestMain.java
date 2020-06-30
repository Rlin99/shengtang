package banyuan.club;

import banyuan.club.dao.IOrderDao;
import banyuan.club.dao.IOrderDetailDao;
import banyuan.club.dao.IProductDao;
import banyuan.club.dao.IUserDao;
import banyuan.club.dao.impl.OrderDaoImpl;
import banyuan.club.dao.impl.OrderDetailImpl;
import banyuan.club.dao.impl.ProductDaoImpl;
import banyuan.club.dao.impl.UserDaoImpl;
import banyuan.club.entity.Order;
import banyuan.club.entity.OrderDetail;

import banyuan.club.entity.Product;
import banyuan.club.service.ProductService;
import banyuan.club.service.UserService;
import banyuan.club.util.JdbcUtils;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//一、准备工作
//1、导入jdbc的jar
//2、复制util/jdbcUtil，dao/IBaseDao、dao/impl/BaseDaoImpl到各自包下
//3、复制配置文件db.properties到src目录
//二、自定义dao
//1、在dao包下创建自定义接口（继承通用BaseDao接口）
//2、在dao/impl下创建自定义接口的实现类
//        以及继承通用接口的实现类BaseDaoImpl
//3、实现自定义接口中的方法


public class TestMain {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        Connection conn = JdbcUtils.getConnection();
        IUserDao userDao = new UserDaoImpl(conn);
        IProductDao productDao = new ProductDaoImpl(conn);
        IOrderDao orderDao = new OrderDaoImpl(conn);
        IOrderDetailDao orderDetailDao = new OrderDetailImpl(conn);

        boolean start = true;
        int choice = 0;
        while(start){
            System.out.println("1：登陆 2：注册  6：退出");
            choice = sc.nextInt();
            switch (choice){
                case 1: userService.userLogin(userDao);
                        while(start){
                            System.out.println("3：购买商品通过名称 4:查询订单信息 6：退出");
                            int choice1 = sc.nextInt();
                            switch (choice1){
                                case 3:productSelectByName(productDao,orderDao,orderDetailDao);
                                    break;
                                case 4:orderDao.getOrder(UserService.userId);
                                    break;
                                case 6:start = false;
                                    break;
                            }
                        }

                    break;
                case 2: userService.userRegister(userDao);
                    break;
                case 6:start = false;
                    break;
            }
        }


    }
    public static void addProduct(IOrderDao orderDao , Product product,IOrderDetailDao orderDetailDao) throws Exception {
        Date date = new Date();
        Order order = new Order(UserService.userId, UserService.loginName, "江苏", date, product.getPrice(),"qweqew");
        int orderId = orderDao.add(order);
        OrderDetail orderDetail = new OrderDetail(orderId,product.getId(),1,product.getPrice());
        orderDetailDao.add(orderDetail);
    }

    public  static void productSelectByName(IProductDao productDao,IOrderDao orderDao,IOrderDetailDao orderDetailDao) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查询商品名称：");
        String name = sc.nextLine();
        List<Product> productList = new ArrayList<>();
        productList = productDao.getProductByName(name);
        for(Product product : productList){
            System.out.println(product);
            addProduct(orderDao,product,orderDetailDao);
        }
    }



}
