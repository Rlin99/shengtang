package club.banyuan.mapper;

import club.banyuan.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
@Mapper
public interface OrderMapper {
    public int addOrder(Order order);
    public List<Order> selectByUserId(Integer userId);
}
