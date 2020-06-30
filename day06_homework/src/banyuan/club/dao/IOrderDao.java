package banyuan.club.dao;

import banyuan.club.entity.Order;
import banyuan.club.entity.Product;

import java.util.List;

public interface IOrderDao extends IBaseDao {
    public int add(Order order) ;
    public void getOrder(Integer userId)throws Exception;
}
