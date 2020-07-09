package club.banyuan.controller;

import club.banyuan.entity.Auction;
import club.banyuan.entity.Product;
import club.banyuan.service.AuctionServiceDao;
import club.banyuan.service.Impl.AuctionServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "LaunchAuctionServlet", urlPatterns = "/lunchAuction.do")
public class LaunchAuctionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
        Auction auction = new Auction();
        Double price = Double.valueOf(request.getParameter("price"));
        Date date = new Date();
        Integer userId = (Integer) session.getAttribute("userId");
        String userName = session.getAttribute("userName").toString();

        auction.setUserId(userId);
        auction.setUesrName(userName);
        auction.setProductId(product.getId());
        auction.setCreateTime(date);
        auction.setPrice(price);

        AuctionServiceDao auctionServiceDao = new AuctionServiceDaoImpl();
        List<Auction> auctionList = new ArrayList<>();
        try {
            auctionServiceDao.add(auction);
            auctionList = auctionServiceDao.getByProductId(product.getId());
            session.setAttribute("auctionList", auctionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("auction.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
