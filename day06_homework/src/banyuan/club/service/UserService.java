package banyuan.club.service;

import banyuan.club.dao.IUserDao;
import banyuan.club.entity.User;

import java.util.Scanner;

public class UserService {
    public static Integer userId = null;
    public static String loginName = null;
    public static String password = null;

    public static void userRegister(IUserDao userDao) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入登陆名：");
        String loginName = sc.nextLine();
        System.out.println("请输入姓名：");
        String userName = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        System.out.println("请选择性别：(1:男 0：女)");
        Integer sex = sc.nextInt();
        System.out.println("请输入邮箱：");
        String email = sc.nextLine();
        System.out.println("请输入手机号：");
        String phone = sc.nextLine();
        User newUser = new User(null,loginName,userName,password,sex,email,phone);
        Integer id = null;
        id = userDao.add(newUser);
        if(id != null){
            System.out.println("注册成功！您的用户ID是：" + id);
        }else{
            System.out.println("注册失败！");
        }
    }


    public static void userLogin(IUserDao userDao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        loginName = sc.nextLine();
        System.out.println("请输入密码：");
        password = sc.nextLine();
        User user = userDao.getLoginUser(loginName,password);
        userId = user.getId();
        if(user != null ){
            System.out.println("登陆成功！欢迎您："+user.getLoginName());
        }else {
            System.out.println("登陆失败！");
        }
    }
}
