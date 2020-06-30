package banyuan.club.dao;

import banyuan.club.entity.OrderDetail;

public interface IOrderDetailDao extends IBaseDao{
    public void add(OrderDetail detail) throws Exception;
}
