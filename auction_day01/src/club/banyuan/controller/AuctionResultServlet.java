package club.banyuan.controller;

import club.banyuan.entity.Auction;
import club.banyuan.entity.Product;
import club.banyuan.service.AuctionServiceDao;
import club.banyuan.service.Impl.AuctionServiceDaoImpl;
import club.banyuan.service.Impl.ProductServiceDaoImpl;
import club.banyuan.service.ProductServiceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AuctionresultServlet", urlPatterns = "/auctionResult.do")
public class AuctionResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();
        List<Product> productListIsSelled = new ArrayList<>();
        AuctionServiceDao auctionServiceDao = new AuctionServiceDaoImpl();
        List<Auction> auctionList = new ArrayList<>();

        try {
            //售出为1  未售出为0
            productListIsSelled = productServiceDao.getProductBySell(1);


            session.setAttribute("productListIsSelled", productListIsSelled);
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.getRequestDispatcher("auctionResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
