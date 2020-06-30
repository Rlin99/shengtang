package banyuan.club.dao.impl;

import banyuan.club.dao.IOrderDetailDao;
import banyuan.club.entity.OrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;

public class OrderDetailImpl extends BaseDaoImpl implements IOrderDetailDao {
    public OrderDetailImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void add(OrderDetail detail) throws Exception {
        Integer id = 0;
        String sql = "insert into order_detail(orderId,productId,quantity,cost) values(?,?,?,?)";
        try{
            Object[] pamas = new Object[]{detail.getOrderId(),detail.getProductId(),detail.getQuantity(),detail.getCost()};
            id = this.executeInsert(sql, pamas);
            detail.setId(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeResource();
        }

    }

    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(rs.getInt("id"));
        orderDetail.setOrderId(rs.getInt("orderId"));
        orderDetail.setProductId(rs.getInt("productId"));
        orderDetail.setQuantity(rs.getInt("quantity"));
        orderDetail.setCost(rs.getDouble("cost"));
        return orderDetail;
    }
}
