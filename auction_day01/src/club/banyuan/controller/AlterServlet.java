package club.banyuan.controller;

import club.banyuan.entity.Product;
import club.banyuan.service.Impl.ProductServiceDaoImpl;
import club.banyuan.service.ProductServiceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AlterServlet", urlPatterns = "/alter.do")
public class AlterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        try {
            Product product = productServiceDao.getProductById(Integer.valueOf(id));
            session.setAttribute("alterProduct", product);
            request.getRequestDispatcher("alter.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
