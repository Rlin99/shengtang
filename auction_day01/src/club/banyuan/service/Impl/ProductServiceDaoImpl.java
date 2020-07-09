package club.banyuan.service.Impl;

import club.banyuan.dao.ProductDao;
import club.banyuan.dao.impl.ProductDaoImpl;
import club.banyuan.entity.Product;
import club.banyuan.service.ProductServiceDao;
import club.banyuan.util.JdbcUtils;

import java.util.List;

public class ProductServiceDaoImpl implements ProductServiceDao {
    @Override
    public List<Product> getProductByName(String proName) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        return productDao.getProductByName(proName);
    }

    @Override
    public Product getProductById(Integer id) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        return productDao.getProductById(id);
    }
}
