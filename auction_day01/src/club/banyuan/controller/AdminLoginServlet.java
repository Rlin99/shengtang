package club.banyuan.controller;

import club.banyuan.entity.Admin;
import club.banyuan.entity.Product;
import club.banyuan.service.AdminServiceDao;
import club.banyuan.service.Impl.AdminServiceDaoImpl;
import club.banyuan.service.Impl.ProductServiceDaoImpl;
import club.banyuan.service.ProductServiceDao;

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

@WebServlet(name = "AdminLoginServlet", urlPatterns = "/adminLogin.do")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        AdminServiceDao adminServiceDao = new AdminServiceDaoImpl();
        HttpSession session = request.getSession();
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();


        Admin admin = null;
        System.out.println(userName+""+password);
        try {
            admin = adminServiceDao.login(userName, password);
            if(admin != null){
                Integer isSelled = 0;//1售出 0 为售出
                List<Product> productListNotSelled = new ArrayList<Product>();
                productListNotSelled = productServiceDao.getProductBySell(isSelled);
                for (Product product : productListNotSelled) {
                    System.out.println(product.getName());
                }
                session.setAttribute("productListNotSelled", productListNotSelled);
                session.setAttribute("admin", admin);
                session.setAttribute("adminId", admin.getId());
                session.setAttribute("adminName", admin.getUserName());
                request.getRequestDispatcher("productControl.jsp").forward(request, response);
                return;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
