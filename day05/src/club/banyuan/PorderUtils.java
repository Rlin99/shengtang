package club.banyuan;

import java.sql.*;


public class PorderUtils {

    public static void addOrder(Integer userId, String loginName, String userAddress, Date createTime, Integer cost) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into porder values(null,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, loginName);
            pstmt.setString(3, userAddress);
            pstmt.setDate(4, (java.sql.Date) createTime);
            pstmt.setInt(5, cost);
            int row = pstmt.executeUpdate();
            if (row >= 1) {
                System.out.println("订单创建成功！");
            } else {
                System.out.println("订单创建失败！");
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

    public static Integer selectId(Integer userId, String loginName, Integer cost) {

        Integer id = 0;

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            String sql = "select id from porder where userId = ? and loginName = ? and cost = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setString(2, loginName);
            pstmt.setInt(3, cost);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.close(pstmt, conn, rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return id;
    }

    public static void SelectAll() {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;


        try {
            conn = JdbcUtils.getConnection();

            String sql = "select p.id,p.userId,p.loginName,p.userAddress,p.createTime,pd.porderId as 订单ID ,pd.productId as 商品ID,pd.quantity,pd.cost from porder p left join porder_detail pd on p.id = pd.porderId;";

            pstmt = conn.prepareStatement(sql);


            rs = pstmt.executeQuery(sql);

            while (rs.next()) {
                Integer pid = rs.getInt(1);
                Integer puserId = rs.getInt(2);
                String name = rs.getString(3);
                String address = rs.getString(4);
                Date date = rs.getDate(5);
                Integer porderId = rs.getInt(6);
                Integer productId = rs.getInt(7);
                Integer quantity = rs.getInt(8);
                Integer cost = rs.getInt(9);
                System.out.println(
                        "Porder{" +
                                "id=" + pid +
                                ", userId=" + puserId +
                                ", loginName='" + name + '\'' +
                                ", userAddress='" + address +
                                ", createTime=" + date +
                                ", 订单ID=" + porderId +
                                ", 商品ID=" + productId +
                                ", 商品数量=" + quantity +
                                ", cost=" + cost +
                                '}'
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtils.close(pstmt, conn, rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
