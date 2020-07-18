package club.banyuan.service;

import club.banyuan.entity.Order;
import club.banyuan.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    public int addOrder(Order order);
    public List<Order> selectOrderByUserId(Integer userId);
    public int addOrderDetail(OrderDetail orderDetail);
    public void createOrder(Order order, List<OrderDetail> orderDetailList);
}
