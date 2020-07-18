package club.banyuan.dao;

import club.banyuan.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public int addOrder(Order order);
    public List<Order> selectByUserId(Integer userId);
}
