package club.banyuan.mapper;

import club.banyuan.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper {
    public List<Product> getProductByName(String proName);
    public Product getProductById(Integer id);
}
