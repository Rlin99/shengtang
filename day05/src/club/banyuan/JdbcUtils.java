package club.banyuan;

import java.sql.*;

public class JdbcUtils {

    private static final String USER = "root";
    private static final String PWD = "rootroot";
    private static final String URL = "jdbc:mysql://localhost:3306/db1?&useSSL=false&serverTimezone=UTC";

    //连接数据库
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER, PWD);
    }

    //关闭数据库
    public static void close(Statement stmt, Connection conn) throws SQLException {
        if( stmt != null){
                stmt.close();
        }

        if( conn != null){
                conn.close();
        }
    }

    //关闭数据库
    public static void close(Statement stmt, Connection conn, ResultSet rs) throws SQLException {
        if( rs != null){
                rs.close();
        }
        close(stmt, conn);
    }
}
