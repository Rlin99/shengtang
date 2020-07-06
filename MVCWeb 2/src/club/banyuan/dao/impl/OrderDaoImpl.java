package club.banyuan.dao.impl;

import club.banyuan.dao.OrderDao;
import club.banyuan.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int add(Order order) {
        String sql = "insert into shoppingstreet.order values(null,?,?,?,?,?,?)";
        Object[] params = new Object[]{order.getUserId(),order.getLoginName(),order.getUserAddress()
        ,order.getCreateTime(),order.getCost(),order.getSerialNumber()};
        int i = executeInsert(sql, params);
        return i;
    }

    @Override
    public List<Order> selectByUserId(Integer userId) throws Exception {
        String sql = "select * from shoppingstreet.order where userId = ?";
        Object[] paras = new Object[]{userId};
        ResultSet rs = executeQuery(sql, paras);
        List<Order> orderList = new ArrayList<>();
        while(rs.next()){
            Order order = tableToClass(rs);
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Order tableToClass(ResultSet rs) throws Exception {
        Order order = new Order();
        order.setId(rs.getInt(1));
        order.setUserId(rs.getInt(2));
        order.setLoginName(rs.getString(3));
        order.setUserAddress(rs.getString(4));
        order.setCreateTime(rs.getDate(5));
        order.setCost(rs.getDouble(6));
        order.setSerialNumber(rs.getString(7));
        return order;
    }
}
