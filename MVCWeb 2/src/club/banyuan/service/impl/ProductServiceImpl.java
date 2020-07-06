package club.banyuan.service.impl;
import club.banyuan.dao.ProductDao;
import club.banyuan.dao.impl.ProductDaoImpl;
import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;
import club.banyuan.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProductByName(String proName) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        List<Product> productList = productDao.getProductByName(proName);
        return productList;
    }

    @Override
    public Product getProductById(int id) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        Product product = productDao.getProductById(id);
        return product;
    }
}
