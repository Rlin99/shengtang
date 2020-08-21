package club.banyuan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Porder_DetailUtils {

    public static void addDetail(Integer porderId, Integer productId, Integer quantity, Integer cost) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into porder_detail values(null,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, porderId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, cost);
            int row = pstmt.executeUpdate();
            if (row >= 1) {
                System.out.println("订单详情创建成功！");
            } else {
                System.out.println("订单详情创建失败！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.close(pstmt, conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
