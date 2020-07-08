package club.banyuan.controller;

import club.banyuan.entity.Admin;
import club.banyuan.service.AdminServiceDao;
import club.banyuan.service.Impl.AdminServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminLoginServlet", urlPatterns = "/adminLogin.do")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        AdminServiceDao adminServiceDao = new AdminServiceDaoImpl();
        Admin admin = null;
        System.out.println(userName+""+password);
        try {
            admin = adminServiceDao.login(userName, password);
            if(admin != null){
                request.getRequestDispatcher("productControl.jsp").forward(request, response);
                return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
