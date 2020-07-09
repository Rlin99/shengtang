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
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search.do")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String describtion = request.getParameter("describtion");
        String startTime = request.getParameter("startTime");
        String finishTime = request.getParameter("finishTime");
        String startPrice = request.getParameter("startPrice");


        String sql = "select * from product where isSelled = 0 ";

        if(name != ""){
            name = " and name like '%" + name + "%' ";
            sql = sql + name;
        }
        if(describtion != ""){
            describtion = " and describtion like '%" + describtion + "%' ";
            sql = sql + describtion;
        }
        if(startTime != ""){
            startTime = " and startTime >= '" + startTime + "' ";
            sql += startTime;
        }
        if(finishTime != ""){
            finishTime = " and finishTime <= '"+ finishTime + "' ";
            sql += finishTime;
        }
        System.out.println(sql);
        HttpSession session = request.getSession();
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();
        try {
            List<Product> productList = productServiceDao.getProductByOpinion(sql);
            session.setAttribute("productListNotSelled", productList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String adminId = session.getAttribute("adminId").toString();
        //因为通过session得到userID和adminID的值，但因为只能存在一个，
        //所以肯定会有一个报错，所以用一个trycatch捕获
        try{
            String userId = session.getAttribute("userId").toString();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (Exception e){
            String adminId = session.getAttribute("adminId").toString();
            request.getRequestDispatcher("productControl.jsp").forward(request, response);
        }
//        if(adminId == ""){
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }else {
//            request.getRequestDispatcher("productControl.jsp").forward(request, response);
//        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
