package club.banyuan.service;

import club.banyuan.entity.Order;
import club.banyuan.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    public int addOrder(Order order) throws SQLException;
    public List<Order> selectOrderByUserId(Integer userId) throws Exception;
    public int addOrderDetail(OrderDetail orderDetail) throws SQLException;
    public void createOrder(Order order, List<OrderDetail> orderDetailList) throws Exception;
}
