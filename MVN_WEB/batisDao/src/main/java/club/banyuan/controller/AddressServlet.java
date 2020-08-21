package club.banyuan.controller;

import club.banyuan.entity.Address;
import club.banyuan.service.UserService;
import club.banyuan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddressServlet", urlPatterns = "/address.do")
public class AddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String address = request.getParameter("country") + request.getParameter("province") +
                request.getParameter("city") + request.getParameter("area");
        session.setAttribute("address", address);
        session.setAttribute("detailAddress", request.getParameter("detailAddress"));
        session.setAttribute("userName", request.getParameter("userName"));
        session.setAttribute("phone", request.getParameter("phone"));
        System.out.println(session.getAttribute("address").toString() + session.getAttribute("detailAddress") + session.getAttribute("userName") + session.getAttribute("phone"));

        //添加address
        UserService userService = new UserServiceImpl();
        Address address1 = new Address();
        address1.setUserId((Integer) session.getAttribute("userId"));
        address1.setAddress(session.getAttribute("address").toString() + "@" + session.getAttribute("detailAddress").toString());
        Date date = new Date();
        address1.setCreateTime(date);
        address1.setIsDefault(0);
        address1.setRemark(session.getAttribute("userName").toString() + "@" + session.getAttribute("phone"));
        try {
            int id = userService.addAddress(address1);
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取address
        List<Address> addressList = new ArrayList<Address>();
        try {
            addressList = userService.getAddress((Integer) session.getAttribute("userId"));
            session.setAttribute("addressList", addressList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("Member_Address.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
