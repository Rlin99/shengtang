package club.banyuan.service.impl;

import club.banyuan.dao.OrderDao;
import club.banyuan.dao.OrderDetailDao;
import club.banyuan.entity.Order;
import club.banyuan.entity.OrderDetail;
import club.banyuan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
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
        return orderDetailDao.addOrderDetail(orderDetail);
    }

    public void createOrder(Order order, List<OrderDetail> orderDetailList){

        try{
            int i = orderDao.addOrder(order);
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetail.setOrderId(i);
                orderDetailDao.addOrderDetail(orderDetail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
