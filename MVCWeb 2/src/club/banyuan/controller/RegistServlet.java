package club.banyuan.controller;

import club.banyuan.dao.UserDao;
import club.banyuan.dao.impl.UserDaoImpl;
import club.banyuan.entity.User;
import club.banyuan.service.UserService;
import club.banyuan.service.impl.UserServiceImpl;
import club.banyuan.util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistServlet", urlPatterns = "/regist.do")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setLoginName(request.getParameter("loginName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        String sex = request.getParameter("sex");
        if(sex.equals("男")){
            user.setSex(1);
        }else {
            user.setSex(0);
        }
        user.setEmail(request.getParameter("email"));
        user.setMobile(request.getParameter("mobile"));
        boolean flag = false;
        try {
            UserService userService = new UserServiceImpl();
            int id = userService.regist(user);
            if(id>0){
                flag = true;
            }
            System.out.println(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(flag){
            response.sendRedirect("login.jsp");
        }else{
            response.sendRedirect("Regist.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
