package club.banyuan.dao;

import club.banyuan.entity.Product;

import java.util.List;

public interface ProductDao extends IBaseDao{
    public List<Product> getProductByName(String proName)throws Exception;
    public Product getProductById(Integer id)throws Exception;
}
