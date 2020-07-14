package club.banyuan.dao;

import club.banyuan.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    public List<Product> getProductByOpinion(Map map);
    public Product getProductById(Integer id);
    public List<Product> getProductBySell(Integer isSelled) ;
    public int updateProduct(Product product);
    public int addProduct(Product product) ;
    public int deleteProduct(Integer id) ;

}
