package club.banyuan.controller;

import club.banyuan.entity.User;
import club.banyuan.service.Impl.UserServiceDaoImpl;
import club.banyuan.service.UserServiceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserServiceDao userServiceDao = new UserServiceDaoImpl();
        User user = null;
        HttpSession session = request.getSession();
        try {
            user = userServiceDao.login(userName, password);
            if(user != null){
                session.setAttribute("user", user);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
