package club.banyuan.service;

import club.banyuan.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    public List<Product> getProductByName(String proName) throws Exception;
    public Product getProductById(int id) throws Exception;
}
