package banyuan.club.dao.impl;

import banyuan.club.dao.IOrderDao;
import banyuan.club.entity.Order;
import banyuan.club.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements IOrderDao {
    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int add(Order order) {
        Integer id = 0;
        String sql = "insert into shoppingStreet.order(userId,loginName,userAddress,createTime,cost,serialNumber) values(?,?,?,?,?,?) ";
        Object[] params = new Object[]{order.getUserId(),order.getLoginName(),order.getUserAddress(),order.getCreateTime(),order.getCost(),order.getSerialNumber()};
        try{
            id = this.executeInsert(sql, params);
            order.setId(new Integer(id).intValue());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeResource();
            return order.getId();
        }

    }

    @Override
    public void getOrder(Integer userId) throws Exception {
        String sql =  "select o.id, o.userId, o.createTime, od.productId,od.quantity,od.cost from shoppingstreet.order o left join order_detail  od on o.id = od.orderId where o.userId = ?";
        List<Object> paramsList = new ArrayList<>();
        ResultSet rs = null;

        try{
            paramsList.add(userId);
            rs = executeQuery(sql, paramsList.toArray());
            while(rs.next()){
                Integer oid = rs.getInt(1);
                Integer ouserId = rs.getInt(2);
                Date date = rs.getDate(3);
                Integer productId = rs.getInt(4);
                Integer quantity = rs.getInt(5);
                Double cost = rs.getDouble(6);
                System.out.println(
                        "订单详情：{" +
                                "订单id=" + oid +
                                ", userId=" + ouserId +
                                //", loginName='" + name + '\'' +
                                //", userAddress='" + address  +
                                ", createTime=" + date +
                                ", 商品ID=" + productId +
                                ", 商品数量=" + quantity +
                                ", cost=" + cost +
                                '}'
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeResource(rs);
            this.closeResource();
        }

    }

    @Override
    public Order tableToClass(ResultSet rs) throws Exception {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("userId"));
        order.setCreateTime(rs.getDate("createTime"));
        order.setCost(rs.getDouble("cost"));
        order.setUserAddress(rs.getString("userAddress"));
        order.setSerialNumber(rs.getString("serialNumber"));
        order.setLoginName(rs.getString("loginName"));
        return order;
    }
}
