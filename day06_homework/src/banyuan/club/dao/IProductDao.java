package banyuan.club.dao;

import banyuan.club.entity.Product;

import java.util.List;

public interface IProductDao extends IBaseDao{
    public List<Product> getProductByName(String proName)throws Exception;
    public Product getProductById(Integer id)throws Exception;
}
