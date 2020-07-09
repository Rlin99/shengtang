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
    public List<Product> getProductByOpinion(String sql) throws Exception {
        List<Product> productList = new ArrayList<>();
        ResultSet rs = executeQuery(sql, new Object[]{});
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
        String sql = "select * from product where id = ?";
        ResultSet rs = executeQuery(sql, new Object[]{id});
        if(rs.next()){
            product = tableToClass(rs);
        }
        return product;
    }

    @Override
    public List<Product> getProductBySell(Integer isSelled) throws Exception {
        List<Product> productList = new ArrayList<>();
        String sql = "select * from product where isSelled = ?";
        ResultSet rs = executeQuery(sql, new Object[]{isSelled});
        while(rs.next()){
            Product product = new Product();
            product = tableToClass(rs);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public int updateProduct(Product product) throws Exception {
        String sql = "update product set name = ? , describtion = ?, startTime = ?, finishTime = ?,startPrice = ?, basePrice = ?,fileName = ? where id = ?";
        Object[] params = new Object[]{product.getName(),product.getDescribtion(),product.getStartTime(),
        product.getFinishTime(),product.getStartPrice(),product.getBasePrice(),product.getFileName(),product.getId()};
        int i = executeUpdate(sql, params);
        return i;
    }

    @Override
    public int addProduct(Product product) throws Exception {
        String sql = "insert into product values(null,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[]{product.getName(),product.getDescribtion(),product.getStartTime(),product.getFinishTime()
        ,product.getStartPrice(),product.getBasePrice(),product.getFileName(),product.getIsSelled()};
        System.out.println(product);
        int i = executeInsert(sql, params);
        return i;
    }

    @Override
    public Product tableToClass(ResultSet rs) throws Exception {
        Product product = new Product();
        product.setId(rs.getInt(1));
        product.setName(rs.getString(2));
        product.setDescribtion(rs.getString(3));
        product.setStartTime(rs.getTimestamp(4));
        product.setFinishTime(rs.getTimestamp(5));
        product.setStartPrice(rs.getDouble(6));
        product.setBasePrice(rs.getDouble(7));
        product.setFileName(rs.getString(8));
        product.setIsSelled(rs.getInt(9));
        return product;
    }
}
