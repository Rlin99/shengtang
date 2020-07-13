package club.banyuan.dao;

import club.banyuan.entity.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProductByName(String proName);
    public Product getProductById(Integer id);
}
