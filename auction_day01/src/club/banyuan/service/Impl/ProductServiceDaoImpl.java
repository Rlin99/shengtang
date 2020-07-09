package club.banyuan.service.Impl;

import club.banyuan.dao.ProductDao;
import club.banyuan.dao.impl.ProductDaoImpl;
import club.banyuan.entity.Product;
import club.banyuan.service.ProductServiceDao;
import club.banyuan.util.JdbcUtils;

import java.util.List;

public class ProductServiceDaoImpl implements ProductServiceDao {

    @Override
    public List<Product> getProductByOpinion(String sql) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        return productDao.getProductByOpinion(sql);
    }

    @Override
    public Product getProductById(Integer id) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getProductBySell(Integer isSelled) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        return  productDao.getProductBySell(isSelled);
    }

    @Override
    public int updateProduct(Product product) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        return productDao.updateProduct(product);
    }

    @Override
    public int addProduct(Product product) throws Exception {
        ProductDao productDao = new ProductDaoImpl(JdbcUtils.getConnection());
        return productDao.addProduct(product);
    }
}
