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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AlterProductServlet",urlPatterns = "/alterProduct.do")
public class AlterProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpSession session = request.getSession();
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();
        //获取修改后的商品属性
        String name = request.getParameter("name");
        String describtion = request.getParameter("describtion");
        String startPrice = request.getParameter("startPrice");
        String basePrice = request.getParameter("basePrice");
        String startTime = request.getParameter("startTime");
        String finishTime = request.getParameter("finishTime");
        String fileName = request.getParameter("fileName");
        //构造成Product对象
        Product product = new Product();
        product.setName(name);
        product.setDescribtion(describtion);
        product.setStartPrice(Double.valueOf(startPrice));
        product.setBasePrice(Double.valueOf(basePrice));
        try {
            product.setStartTime(sdf.parse(startTime));
            product.setFinishTime(sdf.parse(finishTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        product.setFileName(fileName);
        //获取要修改的商品ID
        Integer alterProductId = (Integer)session.getAttribute("alterProductId");
        product.setId(alterProductId);


        int i = 0;
        try {
            i = productServiceDao.updateProduct(product);
            if(i != 0){

                List<Product> productListNotSelled = new ArrayList<Product>();
                productListNotSelled = productServiceDao.getProductBySell(0);

                session.setAttribute("productListNotSelled", productListNotSelled);

                request.getRequestDispatcher("productControl.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("alter.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
