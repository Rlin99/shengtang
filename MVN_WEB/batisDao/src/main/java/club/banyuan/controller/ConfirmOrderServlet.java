package club.banyuan.controller;

import club.banyuan.entity.Address;
import club.banyuan.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ConfirmOrderServlet", urlPatterns = "/confirmOrder.do")
public class ConfirmOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if((User)session.getAttribute("user") == null){
            request.getRequestDispatcher("login.html").forward(request, response);
            return;
        }
            //商品总价，默认地址

        //获取address
        List<Address> addressList = new ArrayList<Address>();
        addressList = (ArrayList<Address>)session.getAttribute("addressList");
            for (Address address : addressList) {
                if(address.getIsDefault() == 1){
                    session.setAttribute("buyCarAddress", address);
                }
            }
            request.getRequestDispatcher("BuyCar_Two.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
