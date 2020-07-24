package club.banyuan.mapper;

import club.banyuan.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    public int addOrderDetail(OrderDetail orderDetail);
}
