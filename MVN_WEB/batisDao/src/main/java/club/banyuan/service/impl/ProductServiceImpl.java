package club.banyuan.service.impl;
import club.banyuan.dao.ProductDao;
import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao;
    @Override
    public List<Product> getProductByName(String proName) {
        return productDao.getProductByName(proName);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
}
