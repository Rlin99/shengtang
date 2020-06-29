package club.banyuan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductUtils {

    //模糊查询商品
    public static void SelectProduct(String pname){

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from product where name like '%"+pname+"%' ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setDescription(rs.getString(3));
                product.setPrice(rs.getInt(4));
                System.out.println(product);
                Date date = new Date(System.currentTimeMillis());
                PorderUtils.addOrder(UserUtils.selectIdByName(Application.username), Application.username, "", date,product.getPrice());
                int id = PorderUtils.selectId(UserUtils.selectIdByName(Application.username),Application.username,product.getPrice());
                Porder_DetailUtils.addDetail(id, product.getId(), 1, product.getPrice());
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(pstmt, conn, rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
