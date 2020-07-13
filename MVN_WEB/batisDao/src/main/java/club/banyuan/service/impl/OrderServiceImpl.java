package club.banyuan.service.impl;

import club.banyuan.dao.OrderDao;
import club.banyuan.dao.OrderDetailDao;
import club.banyuan.entity.Order;
import club.banyuan.entity.OrderDetail;
import club.banyuan.service.OrderService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDetailDao orderDetailDao;
    OrderDao orderDao;
    @Override
    public int addOrder(Order order) {
        return addOrder(order);
    }

    @Override
    public List<Order> selectOrderByUserId(Integer userId) {
        return orderDao.selectByUserId(userId);
    }

    @Override
    public int addOrderDetail(OrderDetail orderDetail) {
        return addOrderDetail(orderDetail);
    }

    public void createOrder(Order order, List<OrderDetail> orderDetailList){


//        Connection conn = JdbcUtils.getConnection();
//        conn.setAutoCommit(false);
//        try{
//            OrderDao orderDao = new OrderDaoImpl(conn);
//            OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(conn);
//            int i = orderDao.add(order);
//            for (OrderDetail orderDetail : orderDetailList) {
//                orderDetail.setOrderId(i);
//                orderDetailDao.add(orderDetail);
//            }
//            conn.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//            conn.rollback();
//            throw new Exception("订单提交失败！");
//        }

    }
}
