package club.banyuan.dao.impl;

import club.banyuan.dao.ProductDao;
import club.banyuan.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Product> getProductByName(String proName) throws Exception {
        List<Product> productList = new ArrayList<>();
        String sql = "select * from product where name like ?";
        String s = "%" + proName + "%";
        ResultSet rs = executeQuery(sql, new Object[]{s});
        while(rs.next()){
            Product product = new Product();
            product = tableToClass(rs);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product getProductById(Integer id) throws Exception {
        Product product = null;
        String sql = "select * from ";
        return null;
    }

    @Override
    public Product tableToClass(ResultSet rs) throws Exception {
        Product product = new Product();
        product.setId(rs.getInt(1));
        product.setName(rs.getString(2));
        product.setDescribtion(rs.getString(3));
        product.setStartTime(rs.getTime(4));
        product.setFinishTime(rs.getTime(5));
        product.setStartPrice(rs.getDouble(6));
        product.setBasePrice(rs.getDouble(7));
        product.setFileName(rs.getString(8));
        product.setIsSelled(rs.getInt(9));
        return product;
    }
}
