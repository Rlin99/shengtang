package club.banyuan.controller;

import club.banyuan.entity.Address;
import club.banyuan.entity.Order;
import club.banyuan.entity.User;
import club.banyuan.service.OrderService;
import club.banyuan.service.UserService;
import club.banyuan.service.impl.OrderServiceImpl;
import club.banyuan.service.impl.UserServiceImpl;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
//        格式验证

//        调用service处理登录请求
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(loginName,password);
            if(user!=null){
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                //获取address
                List<Address> addressList = new ArrayList<Address>();
                try {
                    addressList = userService.getAddress((user.getId()));
                    for (Address address : addressList) {
                        if(address.getIsDefault() == 1){
                            session.setAttribute("defaultAddress", address.getAddress());
                        }
                    }
                    session.setAttribute("addressList", addressList);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //获取用户订单信息
                OrderService orderService = new OrderServiceImpl();
                List<Order> orderList = new ArrayList<>();
                orderList = orderService.selectOrderByUserId(user.getId());
                session.setAttribute("orderList", orderList);
                request.getRequestDispatcher("index.jsp").forward(request,response);


                return ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("login.html").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
