package club.banyuan.service.impl;
import club.banyuan.entity.Product;
import club.banyuan.mapper.ProductMapper;
import club.banyuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productDao;
    @Override
    public List<Product> getProductByName(String proName) {
        return productDao.getProductByName(proName);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
}
