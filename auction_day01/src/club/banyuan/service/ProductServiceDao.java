package club.banyuan.service;

import club.banyuan.entity.Product;

import java.util.List;

public interface ProductServiceDao {
    public List<Product> getProductByName(String proName) throws Exception;
    public Product getProductById(Integer id) throws Exception;
}
