package club.banyuan;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserUtils {

    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入用户名：");
//        String user = sc.nextLine();
//        System.out.println("请输入密码：");
//        String pwd = sc.nextLine();
//        System.out.println("正在登陆...");
        //login(user, pwd);

        SelectAll();

//        try {
//            validate(user, pwd);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }

    public static void login(String user, String pwd) {

        Connection conn = null;
        Statement stmt =null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            stmt = conn.createStatement();

            String sql1 = String.format("select * from user where uesrname = '%s' and password = '%s';", user,pwd);

            String sql = "select * from user where uesrname = '"+user+"' and password = '"+pwd+"';";

            System.out.println(sql1);

            rs = stmt.executeQuery(sql1);

            if(rs.next()){
                System.out.println("登陆成功，欢迎您：" + user);
            }else {
                System.out.println("登陆失败！");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(stmt, conn, rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static boolean validate(String user, String pwd) throws SQLException {

        Connection conn = JdbcUtils.getConnection();
        String sql = "select * from user where uesrname = ? and password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user);
        pstmt.setString(2, pwd);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            System.out.println("登陆成功，欢迎您：" + user);
            JdbcUtils.close(pstmt, conn, rs);
            return true;
        }else {
            System.out.println("登陆失败！");
            JdbcUtils.close(pstmt, conn, rs);
            return false;
        }

    };

    public static void SelectAll(){

        List<User> userList = new ArrayList<>();

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;


        try{
            conn = JdbcUtils.getConnection();

            String sql = "select * from user;";

            pstmt = conn.prepareStatement(sql);


            rs = pstmt.executeQuery(sql);

            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                userList.add(user);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(pstmt, conn, rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        for(User user : userList){
            System.out.println(user);
        }
    }

    public static Integer selectIdByName(String username){

        Integer id = 0;

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();

            String sql = "select id from user where uesrname = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);

            rs = pstmt.executeQuery();

            while(rs.next()){
                id = rs.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(pstmt, conn, rs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return id;
    }

}
