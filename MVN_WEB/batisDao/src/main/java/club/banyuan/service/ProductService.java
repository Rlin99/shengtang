package club.banyuan.service;

import club.banyuan.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProductByName(String proName);

    public Product getProductById(int id);
}
