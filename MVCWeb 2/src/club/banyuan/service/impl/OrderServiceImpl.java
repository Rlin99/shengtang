package club.banyuan.service.impl;

import club.banyuan.dao.OrderDao;
import club.banyuan.dao.OrderDetailDao;
import club.banyuan.dao.impl.OrderDaoImpl;
import club.banyuan.dao.impl.OrderDetailDaoImpl;
import club.banyuan.entity.Order;
import club.banyuan.entity.OrderDetail;
import club.banyuan.service.OrderService;
import club.banyuan.util.JdbcUtils;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public int addOrder(Order order) throws SQLException {
        OrderDao orderDao = new OrderDaoImpl(JdbcUtils.getConnection());
        int i = orderDao.add(order);
        return i;
    }

    @Override
    public List<Order> selectOrderByUserId(Integer userId) throws Exception {
        OrderDao orderDao = new OrderDaoImpl(JdbcUtils.getConnection());
        List<Order> orderList = orderDao.selectByUserId(userId);
        return orderList;
    }

    @Override
    public int addOrderDetail(OrderDetail orderDetail) throws SQLException {
        OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(JdbcUtils.getConnection());
        return orderDetailDao.add(orderDetail);
    }

    public void createOrder(Order order, List<OrderDetail> orderDetailList) throws  Exception{
        Connection conn = JdbcUtils.getConnection();
        conn.setAutoCommit(false);
        try{
            OrderDao orderDao = new OrderDaoImpl(conn);
            OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(conn);
            int i = orderDao.add(order);
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetail.setOrderId(i);
                orderDetailDao.add(orderDetail);
            }
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
            throw new Exception("订单提交失败！");
        }

    }
}
