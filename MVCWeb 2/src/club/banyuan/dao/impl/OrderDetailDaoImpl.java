package club.banyuan.dao.impl;

import club.banyuan.dao.OrderDetailDao;
import club.banyuan.entity.OrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;

public class OrderDetailDaoImpl extends BaseDaoImpl implements OrderDetailDao {
    public OrderDetailDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int add(OrderDetail orderDetail) {
        String sql = "insert into order_detail values(null,?,?,?,?)";
        Object[] params = new Object[]{orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getQuantity(),orderDetail.getCost()};
        int i = executeInsert(sql, params);
        return i;
    }

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        return null;
    }
}
