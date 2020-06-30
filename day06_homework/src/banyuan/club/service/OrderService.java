package banyuan.club.service;

import banyuan.club.dao.IOrderDao;
import banyuan.club.dao.IOrderDetailDao;
import banyuan.club.dao.IProductDao;
import banyuan.club.dao.impl.OrderDaoImpl;
import banyuan.club.dao.impl.OrderDetailImpl;
import banyuan.club.dao.impl.ProductDaoImpl;
import banyuan.club.entity.Product;
import banyuan.club.util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderService {
    Connection conn;

    {
        try {
            conn = JdbcUtils.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    IProductDao productDao = new ProductDaoImpl(conn);
    IOrderDao orderDao = new OrderDaoImpl(conn);
    IOrderDetailDao orderDetailDao = new OrderDetailImpl(conn);
    public static void addProduct(Product product){

    }
}
