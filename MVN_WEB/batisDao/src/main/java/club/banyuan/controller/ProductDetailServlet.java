package club.banyuan.controller;

import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;
import club.banyuan.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductDetailServlet",urlPatterns = "/detail.do")
public class ProductDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        缺id的非空判断
        int id = Integer.valueOf(request.getParameter("id"));
        ProductService productService = new ProductServiceImpl();
        try {
            Product product = productService.getProductById(id);
            request.setAttribute("product",product);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        request.getRequestDispatcher("product.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
