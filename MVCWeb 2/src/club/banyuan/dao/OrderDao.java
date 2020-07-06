package club.banyuan.dao;

import club.banyuan.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends IBaseDao{
    public int add(Order order);
    public List<Order> selectByUserId(Integer userId) throws Exception;
}
