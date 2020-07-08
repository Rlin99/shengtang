package club.banyuan.controller;

import club.banyuan.entity.User;
import club.banyuan.service.Impl.UserServiceDaoImpl;
import club.banyuan.service.UserServiceDao;

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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String postCode = request.getParameter("postCode");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setIdCard(idCard);
        user.setPhone(phone);
        user.setAddress(address);
        user.setPostCode(postCode);
        System.out.println(user);

        UserServiceDao userServiceDao = new UserServiceDaoImpl();
        try {
            int i = -1;
            i = userServiceDao.regist(user);
            if(i != -1){
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("regist.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
