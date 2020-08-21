package club.banyuan;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static String username;
    public static String password;

    public static void buy() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要购买商品的名称：");
        String pname = sc.nextLine();
        ProductUtils.SelectProduct(pname);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        username = sc.nextLine();
        System.out.println("请输入密码：");
        password = sc.nextLine();
        boolean f = false;
        try {
            f = UserUtils.validate(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (f) {
            System.out.println("请选择：");
            System.out.println("1：购买");
            System.out.println("2：查询订单信息+详情");
            System.out.println("3：退出");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    buy();
                    break;
                case 2:
                    PorderUtils.SelectAll();
                    break;
                case 3:
                    f = false;
                    break;
            }
        }


    }
}
