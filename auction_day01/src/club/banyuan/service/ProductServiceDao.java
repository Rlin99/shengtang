package club.banyuan.service;

import club.banyuan.entity.Product;

import java.util.List;

public interface ProductServiceDao {
    public List<Product> getProductByOpinion(String sql) throws Exception;
    public Product getProductById(Integer id) throws Exception;
    public List<Product> getProductBySell(Integer isSelled) throws Exception;
    public int updateProduct(Product product) throws Exception;
    public int addProduct(Product product) throws Exception;
}
